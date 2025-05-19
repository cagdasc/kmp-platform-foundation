package com.cacaosd.platform.coroutines.di

import com.cacaosd.platform.coroutines.dispatchers.PlatformDispatchers
import com.cacaosd.platform.coroutines.dispatchers.getPlatformDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinApplication
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

fun KoinApplication.platformCoroutines(): KoinApplication {
    koin.loadModules(
        modules = listOf(
            module {
                single { getPlatformDispatchers() } bind PlatformDispatchers::class
                factory(qualifier = ioScopeQualifier) {
                    val platformDispatchers = get<PlatformDispatchers>()
                    CoroutineScope(platformDispatchers.io + SupervisorJob())
                }
                factory(qualifier = mainScopeQualifier) {
                    val platformDispatchers = get<PlatformDispatchers>()
                    CoroutineScope(platformDispatchers.main + SupervisorJob())
                }
            }
        )
    )
    return this
}

internal object IoScopeQualifier

val ioScopeQualifier = TypeQualifier(IoScopeQualifier::class)

internal object MainScopeQualifier

val mainScopeQualifier = TypeQualifier(MainScopeQualifier::class)
