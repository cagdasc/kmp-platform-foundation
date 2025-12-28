package com.cacaosd.platform.core.application_info

import com.cacaosd.platform.core.config.PlatformConfiguration

expect class PlatformAppInfo

expect fun PlatformConfiguration.findApplicationInfo(
    packageName: String,
    onError: (Throwable) -> Unit
): PlatformAppInfo?
