package com.example.appremedio.db.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "consumo",
    foreignKeys = [
        ForeignKey(
            entity = PessoaRemedio::class,
            parentColumns = ["id"],
            childColumns = ["idPessoaRemedio"],
            onDelete = CASCADE
        )
    ]
)

data class Consumo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val idPessoaRemedio: Long,
    val datetimeAgendado: Long?,
    val datetimeConsumo: Long?
)
