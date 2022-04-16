package com.dedechandran.quranapps.domain

import com.dedechandran.quranapps.common.DispatcherProvider
import com.dedechandran.quranapps.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSurahUseCase @Inject constructor(
    private val repository: QuranRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    fun getSurah(): Flow<Result<List<Surah>>> {
        return repository.getSurah()
            .flowOn(dispatcherProvider.io)
    }

}