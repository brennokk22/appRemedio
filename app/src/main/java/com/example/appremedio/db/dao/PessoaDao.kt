package com.example.appremedio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appremedio.db.entidades.Pessoa

@Dao
interface PessoaDao {
    @Insert
    fun inserirPessoa(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa")
    fun buscarPessoas(): List<Pessoa>

    @Query("SELECT count(*) FROM pessoa")
    fun contarPessoas(): Int
}