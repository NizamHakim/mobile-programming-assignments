package com.example.tugas_456789_kt.util

sealed class GeneralEvent {
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): GeneralEvent()
}