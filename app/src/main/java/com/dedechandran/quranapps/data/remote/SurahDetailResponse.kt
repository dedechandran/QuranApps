package com.dedechandran.quranapps.data.remote

import com.google.gson.annotations.SerializedName

data class SurahDetailResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("sequence")
    val sequence: Int,
    @SerializedName("numberOfVerses")
    val numberOfVerses: Int,
    @SerializedName("name")
    val name: SurahName,
    @SerializedName("revelation")
    val revelation: Revelation,
    @SerializedName("tafsir")
    val tafsir: Tafsir,
    @SerializedName("preBismillah")
    val preBismillah: PreBismillah?,
    @SerializedName("verses")
    val verses: List<Verse>
) {

    data class SurahName(
        @SerializedName("short")
        val short: String,
        @SerializedName("long")
        val long: String,
        @SerializedName("transliteration")
        val transliteration: Transliteration,
        @SerializedName("translation")
        val translation: Translation
    ) {
        data class Transliteration(
            @SerializedName("en")
            val en: String,
            @SerializedName("id")
            val id: String
        )

        data class Translation(
            @SerializedName("en")
            val en: String,
            @SerializedName("id")
            val id: String
        )
    }

    data class Revelation(
        @SerializedName("arab")
        val arab: String,
        @SerializedName("en")
        val en: String,
        @SerializedName("id")
        val id: String
    )

    data class Tafsir(
        @SerializedName("id")
        val id: String
    )

    data class PreBismillah(
        @SerializedName("text")
        val text: Text,
        @SerializedName("translation")
        val translation: Translation,
        @SerializedName("audio")
        val audio: Audio
    ) {
        data class Text(
            @SerializedName("arab")
            val arab: String,
            @SerializedName("transliteration")
            val transliteration: Transliteration
        ){
            data class Transliteration(
                @SerializedName("en")
                val en: String
            )
        }

        data class Translation(
            @SerializedName("en")
            val en: String,
            @SerializedName("id")
            val id: String
        )

        data class Audio(
            @SerializedName("primary")
            val primary: String,
            @SerializedName("secondary")
            val secondary: List<String>
        )
    }

    data class Verse(
        @SerializedName("number")
        val number: Number,
        @SerializedName("text")
        val text: Text,
        @SerializedName("translation")
        val translation: Translation
    ){

        data class Number(
            @SerializedName("inQuran")
            val inQuran: Int,
            @SerializedName("inSurah")
            val inSurah: Int
        )

        data class Text(
            @SerializedName("arab")
            val arab: String,
            @SerializedName("transliteration")
            val transliteration: Transliteration
        ) {
            data class Transliteration(
                @SerializedName("en")
                val en: String
            )
        }

        data class Translation(
            @SerializedName("en")
            val en: String,
            @SerializedName("id")
            val id: String
        )
    }
}