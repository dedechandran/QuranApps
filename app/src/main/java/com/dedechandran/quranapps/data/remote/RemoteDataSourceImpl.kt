package com.dedechandran.quranapps.data.remote

import com.dedechandran.quranapps.common.network.GenericResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: QuranService):
    RemoteDataSource {
    override suspend fun getSurah(): GenericResponse<List<SurahResponse>> {
        return service.getSurah()
    }

    override suspend fun getSurahDetails(surahNumber: Int): GenericResponse<SurahDetailResponse> {
        return service.getSurahDetails(surahNumber)
    }
}