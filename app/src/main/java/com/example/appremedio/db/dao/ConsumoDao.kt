package com.example.appremedio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appremedio.db.entidades.Consumo

@Dao
interface ConsumoDao {
    @Insert
    fun inserirConsumo(consumo: Consumo)

    @Query("SELECT * FROM consumo")
    fun buscarConsumo(): List<Consumo>

    @Query("SELECT * FROM consumo ORDER BY datetimeAgendado")
    fun buscarConsumoOrdernado(): List<Consumo>

    @Query("DELETE FROM consumo")
    fun deletarTodosConsumo()

}