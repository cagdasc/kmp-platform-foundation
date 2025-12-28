package com.cacaosd.platform.core.application_info

import com.cacaosd.platform.core.config.PlatformConfiguration

actual data class PlatformAppInfo(val appName: String)

actual fun PlatformConfiguration.findApplicationInfo(
    packageName: String,
    onError: (Throwable) -> Unit
): PlatformAppInfo? {
    return null
}
