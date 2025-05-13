package com.cacaosd.platform.tools.intent

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.speech.RecognizerIntent
import androidx.core.net.toUri
import com.cacaosd.platform.tools.application_info.api33Safe
import com.cacaosd.platform.tools.config.PlatformConfiguration

actual fun getIntentUtil(config: PlatformConfiguration): IntentUtil {
    return AndroidIntentUtil(config.context)
}

class AndroidIntentUtil(private val context: Context) : IntentUtil {

    private val packageManager: PackageManager = context.packageManager

    override fun getOpenInAppIntent(data: String, title: String): Intent {
        val openAppIntent = Intent(
            Intent.ACTION_VIEW,
            data.toUri()
        )
        return Intent.createChooser(openAppIntent, title)
    }

    override fun openInApp(activity: Activity, data: String, title: String, onError: (Throwable) -> Unit) {
        val createChooser = getOpenInAppIntent(data = data, title = title)
        try {
            activity.startActivity(createChooser)
        } catch (e: ActivityNotFoundException) {
            onError(e)
        }
    }

    override fun shareText(activity: Activity, data: String, title: String) {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, data)
            putExtra(Intent.EXTRA_TITLE, title)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
        }, null)
        activity.startActivity(share)
    }

    override fun openPlayStore(marketUri: String, marketUrl: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, marketUri.toUri()).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            })
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    marketUrl.toUri()
                ).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                })
        }
    }

    override fun hasSpeechRecognizer(): Boolean {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        return packageManager.api33Safe(
            tiramisuOrGreater = {
                queryIntentActivities(
                    intent,
                    PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
                )
            },
            lowers = {
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            }
        ).isNotEmpty()
    }

    override fun openNotificationSettings() {
        val intent = Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            } else {
                action = "android.settings.APP_NOTIFICATION_SETTINGS"
                putExtra("app_package", context.packageName)
                putExtra("app_uid", context.applicationInfo.uid)
            }
        }
        context.startActivity(intent)
    }
}