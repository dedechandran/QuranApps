package com.dedechandran.quranapps.domain

import com.dedechandran.quranapps.common.DispatcherProvider
import com.dedechandran.quranapps.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSurahDetailUseCase @Inject constructor(
    private val repository: QuranRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    fun getSurahDetails(surahNumber: Int): Flow<Result<SurahDetail>> {
        return repository.getSurahDetails(surahNumber)
            .flowOn(dispatcherProvider.io)
    }

}