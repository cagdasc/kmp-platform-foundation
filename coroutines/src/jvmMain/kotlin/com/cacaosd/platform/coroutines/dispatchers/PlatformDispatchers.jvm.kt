package com.cacaosd.platform.coroutines.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.swing.Swing

actual fun getPlatformDispatchers(): PlatformDispatchers = object : PlatformDispatchers {
    override val main: MainCoroutineDispatcher
        get() = Dispatchers.Swing
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined

}