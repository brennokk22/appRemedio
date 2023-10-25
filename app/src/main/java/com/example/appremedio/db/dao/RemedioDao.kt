package com.example.appremedio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appremedio.db.entidades.Remedio

@Dao
interface RemedioDao {
    @Insert
    fun inserirRemedio(remedio: Remedio)

    @Query("SELECT * FROM remedio")
    fun buscarRemedios(): List<Remedio>

    @Query("SELECT * FROM remedio where id = :id")
    fun buscaRemedioId(id : Long) : List<Remedio>
}
