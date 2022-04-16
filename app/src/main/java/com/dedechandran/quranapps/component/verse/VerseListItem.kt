package com.dedechandran.quranapps.component.verse

sealed class VerseListItem {
    data class Value(
        val number: String,
        val verseInArab: String,
        val verseInEnglish: String,
        val translationId: String,
        val translationEn: String,
    ): VerseListItem()

    data class Bismillah(
        val value: String
    ): VerseListItem()
}