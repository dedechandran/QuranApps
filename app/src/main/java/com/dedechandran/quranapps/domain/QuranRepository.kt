package com.dedechandran.quranapps.domain

import com.dedechandran.quranapps.common.Result
import kotlinx.coroutines.flow.Flow

interface QuranRepository {

    fun getSurah(): Flow<Result<List<Surah>>>

    fun getSurahDetails(surahNumber: Int): Flow<Result<SurahDetail>>

    fun getLastRead(): Flow<LastRead>

    fun setLastRead(lastRead: LastRead): Flow<Unit>
}