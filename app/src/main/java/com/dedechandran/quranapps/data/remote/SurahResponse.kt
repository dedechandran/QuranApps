package com.dedechandran.quranapps.data.remote

import com.google.gson.annotations.SerializedName

data class SurahResponse(
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
}
