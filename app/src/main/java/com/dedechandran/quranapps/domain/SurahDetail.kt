package com.dedechandran.quranapps.domain

data class SurahDetail(
    val id: Int,
    val surahNameInArab: String,
    val surahName: String,
    val surahNumber: Int,
    val surahTranslation: String,
    val surahRevelation: String,
    val surahVerseNumber: String,
    val surahVerses: List<Verse>,
    val surahPreBismillah: PreBismillah?
) {

    data class PreBismillah(
        val arab: String,
        val translationEn: String,
        val translationId: String,
    )

    data class Verse(
        val verseNumber: String,
        val verseInArab: String,
        val verseInEnglish: String,
        val verseTranslationId: String,
        val verseTranslationEn: String
    )

}
