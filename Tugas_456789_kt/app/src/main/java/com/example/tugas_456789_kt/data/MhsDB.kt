package com.example.tugas_456789_kt.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Mhs::class],
    version = 2,
    exportSchema = false
)
abstract class MhsDB: RoomDatabase() {

    abstract val dao: MhsDao
}