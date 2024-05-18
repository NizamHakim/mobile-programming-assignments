package com.example.tugas_456789_kt.presentation.deletemhs

sealed class DeleteEvent {
    data class OnNrpChange(val nrp: String): DeleteEvent()
    data object OnDeleteButtonClick: DeleteEvent()
    data object OnDeleteDialogButtonClick: DeleteEvent()
    data object OnCancelDialogButtonClick: DeleteEvent()
}