package br.com.alura.leilao.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import br.com.alura.leilao.database.contrato.UsuarioContrato

open class DatabaseHelper protected constructor(context: Context) : SQLiteOpenHelper(context, NOME_BANCO_DE_DADOS, null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABELA_USUARIO)
    }

    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, versaoNova: Int) {

    }

    companion object {
        private val NOME_BANCO_DE_DADOS = "leilao-db"
        private val VERSAO = 1
        private val TABELA_USUARIO = ("CREATE TABLE " + UsuarioContrato.TABELA_NOME + " ("
                + UsuarioContrato._ID + " INTEGER PRIMARY KEY,"
                + UsuarioContrato.CHAVE_NOME + " TEXT)")
    }

}
