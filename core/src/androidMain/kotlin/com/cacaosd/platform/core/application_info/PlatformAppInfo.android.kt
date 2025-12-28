package com.cacaosd.platform.core.application_info

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import com.cacaosd.platform.core.config.PlatformConfiguration

actual typealias PlatformAppInfo = ApplicationInfo

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU, lambda = 1)
fun <T : Any> PackageManager.api33Safe(
    tiramisuOrGreater: PackageManager.() -> T,
    lowers: PackageManager.() -> T
): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        tiramisuOrGreater(this)
    } else {
        lowers(this)
    }
}

actual fun PlatformConfiguration.findApplicationInfo(
    packageName: String,
    onError: (Throwable) -> Unit
): PlatformAppInfo? {
    return with(this.context.packageManager) {
        try {
            api33Safe(
                tiramisuOrGreater = {
                    getApplicationInfo(
                        packageName,
                        PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong())
                    )
                },
                lowers = {
                    getApplicationInfo(packageName, PackageManager.GET_META_DATA)
                }
            )

        } catch (ex: PackageManager.NameNotFoundException) {
            onError(ex)
            null
        }
    }
}