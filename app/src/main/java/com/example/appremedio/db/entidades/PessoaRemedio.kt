package com.example.appremedio.db.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "pessoa_remedio",
    foreignKeys = [ForeignKey(
        entity = Pessoa::class,
        parentColumns = ["id"],
        childColumns = ["idPessoa"],
        onDelete = CASCADE
    ),
        ForeignKey(
            entity = Remedio::class,
            parentColumns = ["id"],
            childColumns = ["idRemedio"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = TipoIntervalo::class,
            parentColumns = ["id"],
            childColumns = ["idTipoIntervalo"],
            onDelete = CASCADE
        )]
)
data class PessoaRemedio(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var idPessoa: Long,
    var idRemedio: Long,
    var idTipoIntervalo: Long,
    val estoque: String,
    val descricao: String
)
