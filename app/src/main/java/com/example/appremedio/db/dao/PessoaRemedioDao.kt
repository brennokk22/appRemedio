package com.example.appremedio.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appremedio.db.entidades.PessoaRemedio

@Dao
interface PessoaRemedioDao {
    @Insert
    fun inserirPessoaRemedio(pessoaremedio: PessoaRemedio)

    @Query("SELECT * FROM pessoa_remedio")
    fun buscarPessoaRemedios(): List<PessoaRemedio>

    @Query("SELECT MAX(id) FROM pessoa_remedio")
    fun buscarMaxId(): Long

    @Query("DELETE FROM pessoa_remedio")
    fun deletarTodosPessoaRemedio()

    @Query("SELECT * FROM pessoa_remedio WHERE id = :id")
    fun buscarPessoaRemediosId(id: Long): List<PessoaRemedio>
}