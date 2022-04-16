package com.dedechandran.quranapps.common.network

import com.google.gson.annotations.SerializedName

data class BaseResult<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T?
)
