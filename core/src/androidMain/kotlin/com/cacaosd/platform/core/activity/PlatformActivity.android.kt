package com.cacaosd.platform.core.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.ui.platform.LocalContext

actual typealias PlatformActivity = Activity

actual typealias PlatformContext = Context

actual inline val LocalPlatformContext get() = LocalContext

actual fun PlatformContext.findActivity(): PlatformActivity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}
