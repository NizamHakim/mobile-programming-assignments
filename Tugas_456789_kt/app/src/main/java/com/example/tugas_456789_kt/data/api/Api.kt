package com.example.tugas_456789_kt.data.api

import com.example.tugas_456789_kt.data.api.models.Contacts
import retrofit2.http.GET

interface Api {

    @GET("Baalmart/8414268/raw/43b0e25711472de37319d870cb4f4b35b1ec9d26/contacts")
    suspend fun getContacts(): Contacts
}