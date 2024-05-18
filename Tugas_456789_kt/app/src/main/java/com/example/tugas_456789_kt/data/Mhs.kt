package com.example.tugas_456789_kt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
class Mhs(
    @PrimaryKey(autoGenerate = false) val nrp: String,
    val nama: String,
    val imageUri: String
)