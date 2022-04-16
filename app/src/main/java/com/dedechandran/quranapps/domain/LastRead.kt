package com.dedechandran.quranapps.domain

data class LastRead(
    val lastReadTimeStamp: Long,
    val lastReadSurahName: String,
    val lastReadSurahNameInArab: String,
    val lastReadSurahNumber: Int,
    val lastReadVerseNumber: Int
)
