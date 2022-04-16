package com.dedechandran.quranapps.domain

import com.dedechandran.quranapps.common.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SetLastReadUseCase @Inject constructor(
    private val repository: QuranRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    fun setLastRead(lastRead: LastRead): Flow<Unit> {
        return repository.setLastRead(lastRead)
            .flowOn(dispatcherProvider.io)
    }

}