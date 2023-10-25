package com.example.appremedio.db

import android.content.Context
import androidx.room.Room
import com.example.appremedio.db.entidades.Pessoa
import com.example.appremedio.db.entidades.Remedio
import com.example.appremedio.db.entidades.TipoIntervalo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class InstanceDb() {
    companion object {
        lateinit var INSTANCE: AppDataBase
        fun build(context: Context) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE =
                    Room.databaseBuilder(context, AppDataBase::class.java, "db_remedio").build()
            }
        }
        suspend fun runDao(bloco: () -> Unit) {
            return withContext(Dispatchers.IO) {
                bloco()
            }
        }


    }

}


