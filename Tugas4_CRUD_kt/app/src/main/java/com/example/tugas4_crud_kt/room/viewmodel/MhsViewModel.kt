package com.example.tugas4_crud_kt.room.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tugas4_crud_kt.room.dao.MhsDao
import com.example.tugas4_crud_kt.room.entity.Mhs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MhsViewModel(private val mhsDao: MhsDao): ViewModel() {

    suspend fun insertMhs(mhs: Mhs): Long{
        return withContext(Dispatchers.IO) {
            mhsDao.insertMhs(mhs)
        }
    }

    fun getMhs(nrp: String): Flow<Mhs>{
        return mhsDao.getMhs(nrp)
    }

    suspend fun updateMhs(oldNrp: String, newNrp: String, newNama: String): Int{
        var status = 0
        try {
            status = mhsDao.updateMhs(oldNrp, newNrp, newNama)
        }catch (e: Exception){
            status = -1
        }
        Log.d("STATUS", "$status")
        return status
    }

}