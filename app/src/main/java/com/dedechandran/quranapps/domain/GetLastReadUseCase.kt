package com.dedechandran.quranapps.domain

import com.dedechandran.quranapps.common.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLastReadUseCase @Inject constructor(
    private val repository: QuranRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    fun getLastRead(): Flow<LastRead> {
        return repository.getLastRead()
            .flowOn(dispatcherProvider.io)
    }

}