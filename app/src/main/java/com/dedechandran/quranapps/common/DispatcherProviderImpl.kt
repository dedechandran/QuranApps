package com.dedechandran.quranapps.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProviderImpl @Inject constructor(
) : DispatcherProvider {
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val main: CoroutineDispatcher = Dispatchers.Main
}