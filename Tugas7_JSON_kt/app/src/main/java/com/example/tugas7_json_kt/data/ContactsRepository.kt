package com.example.tugas7_json_kt.data

import com.example.tugas7_json_kt.data.model.Contact
import com.example.tugas7_json_kt.data.model.Contacts
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    suspend fun getContactsList(): Flow<Result<List<Contact>>>
}