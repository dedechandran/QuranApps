package com.dedechandran.quranapps.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}