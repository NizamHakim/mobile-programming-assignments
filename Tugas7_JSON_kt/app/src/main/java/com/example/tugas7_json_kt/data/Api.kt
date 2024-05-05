package com.example.tugas7_json_kt.data

import com.example.tugas7_json_kt.data.model.Contacts
import retrofit2.http.GET

interface Api {

    @GET("Baalmart/8414268/raw/43b0e25711472de37319d870cb4f4b35b1ec9d26/contacts")
    suspend fun getContactsList(): Contacts

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/"
    }
}