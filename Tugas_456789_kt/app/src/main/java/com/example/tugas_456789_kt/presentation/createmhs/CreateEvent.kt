package com.example.tugas_456789_kt.presentation.createmhs

sealed class CreateEvent {
    data class OnNrpChange(val nrp: String): CreateEvent()
    data class OnNamaChange(val nama: String): CreateEvent()
    data object OnAddButtonClick: CreateEvent()
}