package com.cacaosd.platform.core.activity

import androidx.compose.runtime.ProvidableCompositionLocal

expect class PlatformActivity()

expect abstract class PlatformContext

expect val LocalPlatformContext: ProvidableCompositionLocal<PlatformContext>

expect fun PlatformContext.findActivity(): PlatformActivity?