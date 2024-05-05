package com.example.tugas4_crud_kt.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugas4_crud_kt.room.dao.MhsDao
import com.example.tugas4_crud_kt.room.entity.Mhs

@Database(
    entities = [Mhs::class],
    version = 1
)
abstract class MhsDB: RoomDatabase() {
    abstract val mhsDao: MhsDao

    companion object {
        @Volatile
        private var Instance: MhsDB? = null

        fun getDatabase(context: Context): MhsDB {
            val tempInstance = Instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context,
                    MhsDB::class.java,
                    "MhsDB"
                ).build()
                Instance = newInstance
                return newInstance
            }
        }
    }
}