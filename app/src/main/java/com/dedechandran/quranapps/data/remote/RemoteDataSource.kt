package com.dedechandran.quranapps.data.remote

import com.dedechandran.quranapps.common.network.GenericResponse

interface RemoteDataSource {

    suspend fun getSurah(): GenericResponse<List<SurahResponse>>

    suspend fun getSurahDetails(surahNumber: Int): GenericResponse<SurahDetailResponse>

}