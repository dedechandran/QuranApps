package com.dedechandran.quranapps.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getLastReadSurahNumber(): Int
    suspend fun getLastReadVerseNumber(): Int
    suspend fun getLastReadTimeStamp(): Long
    suspend fun getLastReadSurahName(): String
    suspend fun getLastReadSurahNameInArab(): String
    suspend fun setLastRead(
        timestamp: Long,
        surahName: String,
        surahNumber: Int,
        verseNumber: Int,
        surahNameInArab: String
    )
}