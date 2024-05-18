package com.example.tugas_456789_kt.presentation.updatescreen

sealed class UpdateEvent {
    data class OnNrpChange(val nrp: String): UpdateEvent()
    data object OnFindButtonClick: UpdateEvent()
    data class OnDialogNrpChange(val nrp: String): UpdateEvent()
    data class OnDialogNamaChange(val nama: String): UpdateEvent()
    data object OnDialogUpdateButtonClick: UpdateEvent()
    data object OnDialogCancelButtonClick: UpdateEvent()
}