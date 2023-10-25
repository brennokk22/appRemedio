package com.example.appremedio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appremedio.db.entidades.TipoIntervalo

@Dao
interface TipoIntervaloDao {
    @Insert
    fun inserirTipoIntervalo(remedio: TipoIntervalo)

    @Query("SELECT * FROM tipo_intervalo")
    fun buscarTiposIntervalos(): List<TipoIntervalo>
}