package com.example.appremedio.db.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val dataNascimento: Long?


)


