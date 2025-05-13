package com.cacaosd.platform.tools.intent

import com.cacaosd.platform.tools.activity.PlatformActivity
import com.cacaosd.platform.tools.config.PlatformConfiguration
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.net.URI

actual fun getIntentUtil(config: PlatformConfiguration): IntentUtil {
    return JvmIntentUtil()
}

class JvmIntentUtil : IntentUtil {
    override fun getOpenInAppIntent(
        data: String,
        title: String
    ): PlatformIntent =
        _root_ide_package_.com.cacaosd.platform.tools.intent.PlatformIntent()

    override fun openInApp(
        activity: PlatformActivity,
        data: String,
        title: String,
        onError: (Throwable) -> Unit
    ) {
        val desktop = Desktop.getDesktop()
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(data))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun shareText(
        activity: PlatformActivity,
        data: String,
        title: String
    ) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(data)
        clipboard.setContents(selection, null)
    }

    override fun openPlayStore(marketUri: String, marketUrl: String) {
        openInApp(activity = PlatformActivity(), data = marketUrl, title = "", onError = {})
    }

    override fun hasSpeechRecognizer(): Boolean = false

    override fun openNotificationSettings() {}
}