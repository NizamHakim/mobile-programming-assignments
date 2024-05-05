package com.example.tugas4_crud_kt.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class Mhs(
    @PrimaryKey
    val nrp : String,
    val nama : String,
)
