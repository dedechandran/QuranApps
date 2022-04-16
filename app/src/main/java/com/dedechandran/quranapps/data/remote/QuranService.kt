package com.dedechandran.quranapps.data.remote

import com.dedechandran.quranapps.common.network.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranService {
    @GET("surah")
    suspend fun getSurah(): GenericResponse<List<SurahResponse>>

    @GET("surah/{surahNumber}")
    suspend fun getSurahDetails(@Path("surahNumber") surahNumber: Int): GenericResponse<SurahDetailResponse>
}