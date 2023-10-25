package com.example.appremedio.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appremedio.db.dao.ConsumoDao
import com.example.appremedio.db.dao.PessoaDao
import com.example.appremedio.db.dao.PessoaRemedioDao
import com.example.appremedio.db.dao.RemedioDao
import com.example.appremedio.db.dao.TipoIntervaloDao
import com.example.appremedio.db.entidades.Consumo
import com.example.appremedio.db.entidades.Pessoa
import com.example.appremedio.db.entidades.PessoaRemedio
import com.example.appremedio.db.entidades.Remedio
import com.example.appremedio.db.entidades.TipoIntervalo

@Database(
    entities = [
        Consumo::class,
        Pessoa::class,
        PessoaRemedio::class,
        Remedio::class,
        TipoIntervalo::class,
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun ConsumoDao(): ConsumoDao
    abstract fun PessoaDao(): PessoaDao
    abstract fun PessoaRemedioDao(): PessoaRemedioDao
    abstract fun RemedioDao(): RemedioDao
    abstract fun TipoIntervaloDao(): TipoIntervaloDao
}