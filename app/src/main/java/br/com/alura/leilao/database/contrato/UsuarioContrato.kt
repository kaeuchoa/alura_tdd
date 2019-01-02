package br.com.alura.leilao.database.contrato

import android.provider.BaseColumns

interface UsuarioContrato : BaseColumns {
    companion object {
        const val TABELA_NOME = "usuarios"
        const val CHAVE_NOME = "nome"
        const val _ID = "_id"
    }

}
