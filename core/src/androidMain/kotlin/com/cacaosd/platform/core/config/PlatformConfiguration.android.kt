package com.cacaosd.platform.core.config

import android.content.Context
import com.cacaosd.platform.core.BuildConfig

actual class PlatformConfiguration(val context: Context) {
    actual fun isDebugBuild(): Boolean {
        return BuildConfig.DEBUG
    }
}
