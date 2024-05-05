package com.example.tugas7_json_kt.data

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T> (data: T?, message: String? = null): Result<T>(data)
    class Error<T> (data: T? = null, message: String): Result<T>(data, message)
}