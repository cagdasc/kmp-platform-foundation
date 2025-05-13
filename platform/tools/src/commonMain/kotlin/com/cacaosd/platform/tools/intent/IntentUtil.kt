package com.cacaosd.platform.tools.intent

import com.cacaosd.platform.tools.activity.PlatformActivity
import com.cacaosd.platform.tools.config.PlatformConfiguration

expect fun getIntentUtil(config: PlatformConfiguration): IntentUtil

interface IntentUtil {
    fun getOpenInAppIntent(data: String, title: String = ""): PlatformIntent

    fun openInApp(activity: PlatformActivity, data: String, title: String = "", onError: (Throwable) -> Unit = {})

    fun shareText(activity: PlatformActivity, data: String, title: String = "")

    fun openPlayStore(marketUri: String, marketUrl: String)

    fun hasSpeechRecognizer(): Boolean

    fun openNotificationSettings()
}