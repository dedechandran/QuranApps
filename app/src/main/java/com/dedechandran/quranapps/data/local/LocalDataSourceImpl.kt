package com.dedechandran.quranapps.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {

    override suspend fun getLastReadSurahNumber(): Int {
        return sharedPreferences.getInt(LAST_READ_SURAH_NUMBER, 0)
    }

    override suspend fun getLastReadVerseNumber(): Int {
        return sharedPreferences.getInt(LAST_READ_VERSE_NUMBER, 0)
    }

    override suspend fun getLastReadTimeStamp(): Long {
        return sharedPreferences.getLong(LAST_READ_TIMESTAMP, 0)
    }

    override suspend fun getLastReadSurahName(): String {
        return sharedPreferences.getString(LAST_READ_SURAH_NAME, "").orEmpty()
    }

    override suspend fun getLastReadSurahNameInArab(): String {
        return sharedPreferences.getString(LAST_READ_SURAH_NAME_IN_ARAB, "").orEmpty()
    }

    override suspend fun setLastRead(
        timestamp: Long,
        surahName: String,
        surahNumber: Int,
        verseNumber: Int,
        surahNameInArab: String
    ) {
        sharedPreferences.edit {
            putString(LAST_READ_SURAH_NAME, surahName)
            putInt(LAST_READ_SURAH_NUMBER, surahNumber)
            putLong(LAST_READ_TIMESTAMP, timestamp)
            putInt(LAST_READ_VERSE_NUMBER, verseNumber)
            putString(LAST_READ_SURAH_NAME_IN_ARAB, surahNameInArab)
            apply()
        }
    }

    companion object {
        private const val LAST_READ_SURAH_NUMBER = "LAST_READ_SURAH_NUMBER"
        private const val LAST_READ_VERSE_NUMBER = "LAST_READ_VERSE_NUMBER"
        private const val LAST_READ_TIMESTAMP = "LAST_READ_TIMESTAMP"
        private const val LAST_READ_SURAH_NAME = "LAST_READ_SURAH_NAME"
        private const val LAST_READ_SURAH_NAME_IN_ARAB = "LAST_READ_SURAH_NAME_IN_ARAB"
    }

}