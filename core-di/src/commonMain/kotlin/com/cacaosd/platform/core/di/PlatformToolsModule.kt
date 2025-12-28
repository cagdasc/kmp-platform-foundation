package com.cacaosd.platform.core.di

import com.cacaosd.platform.core.activity.PlatformActivity
import com.cacaosd.platform.core.config.PlatformConfiguration
import com.cacaosd.platform.core.intent.IntentUtil
import com.cacaosd.platform.core.intent.getIntentUtil
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.platformConfiguration(platformConfiguration: PlatformConfiguration): KoinApplication {
    koin.loadModules(
        modules = listOf(
            module {
                single { platformConfiguration }
                single { PlatformActivity() }
                single<IntentUtil> { getIntentUtil(get()) }
            }
        )
    )
    return this
}
