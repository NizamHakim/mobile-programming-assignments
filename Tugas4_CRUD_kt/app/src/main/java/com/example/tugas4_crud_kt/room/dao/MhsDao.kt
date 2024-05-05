package com.example.tugas4_crud_kt.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tugas4_crud_kt.room.entity.Mhs
import kotlinx.coroutines.flow.Flow

@Dao
interface MhsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMhs(mhs: Mhs): Long

    @Query("SELECT * FROM mahasiswa WHERE nrp = :nrp")
    fun getMhs(nrp: String): Flow<Mhs>

    @Query("UPDATE mahasiswa SET nrp=:newNrp, nama=:newNama WHERE nrp=:oldNrp")
    suspend fun updateMhs(oldNrp: String, newNrp: String, newNama: String): Int

    @Delete
    suspend fun deleteMhs(mhs: Mhs)
}