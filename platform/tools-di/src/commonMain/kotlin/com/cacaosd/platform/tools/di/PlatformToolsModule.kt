package com.cacaosd.platform.tools.di

import com.cacaosd.platform.tools.config.PlatformConfiguration
import com.cacaosd.platform.tools.intent.IntentUtil
import com.cacaosd.platform.tools.intent.getIntentUtil
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.platformConfiguration(platformConfiguration: PlatformConfiguration): KoinApplication {
    koin.loadModules(
        modules = listOf(
            module {
                single { platformConfiguration }
                single { _root_ide_package_.com.cacaosd.platform.tools.activity.PlatformActivity() }
                single<IntentUtil> { getIntentUtil(get()) }
            }
        )
    )
    return this
}
