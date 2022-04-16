package com.dedechandran.quranapps.common

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class ApiError(val code: Int, val message: String): Result<Nothing>()
    data class Failure(val message: String): Result<Nothing>()
}
