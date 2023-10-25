package com.example.appremedio.db.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remedio")
data class Remedio(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val tipo: String
) {
    override fun toString(): String {
        return "$nome - $tipo"
    }
}

