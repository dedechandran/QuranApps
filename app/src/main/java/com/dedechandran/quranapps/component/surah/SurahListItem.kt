package com.dedechandran.quranapps.component.surah

sealed class SurahListItem {
    data class Value(
        val number: String,
        val nameInArab: String,
        val name: String,
        val revelation: String,
        val verseNumber: String,
    ): SurahListItem()

}