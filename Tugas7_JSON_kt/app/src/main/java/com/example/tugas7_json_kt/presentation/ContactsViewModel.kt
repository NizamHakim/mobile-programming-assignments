package com.example.tugas7_json_kt.presentation

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas7_json_kt.data.ContactsRepository
import com.example.tugas7_json_kt.data.Result
import com.example.tugas7_json_kt.data.model.Contact
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val contactsRepository: ContactsRepository
): ViewModel(){
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts = _contacts.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            contactsRepository.getContactsList().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { contacts ->
                            _contacts.update { contacts }
                        }
                    }
                }
            }
        }
    }
}