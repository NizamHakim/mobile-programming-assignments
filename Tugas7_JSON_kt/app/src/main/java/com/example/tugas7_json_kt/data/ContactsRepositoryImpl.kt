package com.example.tugas7_json_kt.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.tugas7_json_kt.data.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ContactsRepositoryImpl(
    private val api: Api
): ContactsRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getContactsList(): Flow<Result<List<Contact>>> {
        return flow {
            val contactsFromApi = try {
                api.getContactsList()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading contacts"))
                return@flow
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading contacts"))
                return@flow
            }catch (e: Exception){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading contacts"))
                return@flow
            }
            emit(Result.Success(data = contactsFromApi.contacts))
        }
    }

}