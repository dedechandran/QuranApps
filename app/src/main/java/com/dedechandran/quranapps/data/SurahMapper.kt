package com.dedechandran.quranapps.data

import com.dedechandran.quranapps.data.remote.SurahDetailResponse
import com.dedechandran.quranapps.data.remote.SurahResponse
import com.dedechandran.quranapps.domain.Surah
import com.dedechandran.quranapps.domain.SurahDetail

fun List<SurahResponse>.toSurahModel(): List<Surah> {
    return map {
        Surah(
            id = it.number,
            surahNameInArab = it.name.short,
            surahName = it.name.transliteration.id,
            surahNumber = it.number,
            surahRevelation = it.revelation.id,
            surahVerseNumber = it.numberOfVerses
        )
    }
}

fun SurahDetailResponse.toSurahDetailModel(): SurahDetail {
    return SurahDetail(
        id = number,
        surahNameInArab = name.short,
        surahName = name.transliteration.id,
        surahNumber = number,
        surahRevelation = revelation.id,
        surahVerseNumber = numberOfVerses.toString(),
        surahTranslation = name.translation.id,
        surahPreBismillah = if (preBismillah == null) {
            null
        } else {
            SurahDetail.PreBismillah(
                arab = preBismillah.text.arab,
                translationEn = preBismillah.translation.en,
                translationId = preBismillah.translation.id
            )
        },
        surahVerses = verses.map {
            SurahDetail.Verse(
                verseNumber = it.number.inSurah.toString(),
                verseInArab = it.text.arab,
                verseInEnglish = it.text.transliteration.en,
                verseTranslationEn = it.translation.en,
                verseTranslationId = it.translation.id
            )
        }
    )
}