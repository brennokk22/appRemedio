package com.example.appremedio.db.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipo_intervalo")
data class TipoIntervalo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val descricao: String,
    val constanteTempo: Long
) {
    override fun toString(): String {
        return descricao
    }
}
