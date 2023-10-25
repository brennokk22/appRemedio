package com.example.appremedio.db

import com.example.appremedio.db.entidades.Pessoa
import com.example.appremedio.db.entidades.Remedio
import com.example.appremedio.db.entidades.TipoIntervalo

class PopulateDb {
    companion object {
        suspend fun run() {
            InstanceDb.runDao {
                InstanceDb.INSTANCE.PessoaDao()
                    .inserirPessoa(Pessoa(nome = "padrao", dataNascimento = 871430400000))
                InstanceDb.INSTANCE.RemedioDao()
                    .inserirRemedio(Remedio(nome = "Dipirona", tipo = "Comprimido"))
                InstanceDb.INSTANCE.TipoIntervaloDao().inserirTipoIntervalo(
                    TipoIntervalo(
                        descricao = "Horas",
                        constanteTempo = 3600000
                    )
                )
                InstanceDb.INSTANCE.TipoIntervaloDao().inserirTipoIntervalo(
                    TipoIntervalo(
                        descricao = "Dia",
                        constanteTempo = 86400000
                    )
                )
                InstanceDb.INSTANCE.TipoIntervaloDao().inserirTipoIntervalo(
                    TipoIntervalo(
                        descricao = "Semana",
                        constanteTempo = 604800000
                    )
                )

            }
        }
    }
}