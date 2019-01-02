package br.com.alura.leilao.database.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.alura.leilao.database.DatabaseHelper
import br.com.alura.leilao.database.contrato.UsuarioContrato
import br.com.alura.leilao.database.contrato.UsuarioContrato.Companion.CHAVE_NOME
import br.com.alura.leilao.database.contrato.UsuarioContrato.Companion.TABELA_NOME
import br.com.alura.leilao.model.Usuario
import java.util.*


class UsuarioDAO(context: Context) : DatabaseHelper(context) {

    fun salva(usuario: Usuario): Usuario {
        val db = writableDatabase
        val valores = preencheValores(usuario)
        val id = db.insert(TABELA_NOME, null, valores)
        return Usuario(usuario.nome,id)
    }

    fun todos(): List<Usuario> {
        val selecionaTodos = "SELECT * FROM " + UsuarioContrato.TABELA_NOME

        val db = readableDatabase
        val c = db.rawQuery(selecionaTodos, null)

        return armazenaUsuariosEmLista(c)
    }

    private fun armazenaUsuariosEmLista(c: Cursor): List<Usuario> {
        val usuarios = ArrayList<Usuario>()
        while (c.moveToNext()) {
            val usuarioRetornado = preencheUsuario(c)
            usuarios.add(usuarioRetornado)
        }
        return usuarios
    }

    private fun preencheValores(usuario: Usuario): ContentValues {
        val valores = ContentValues()
        valores.put(CHAVE_NOME, usuario.nome)
        return valores
    }

    private fun preencheUsuario(c: Cursor): Usuario {
        val id = c.getLong(c.getColumnIndex(UsuarioContrato._ID))
        val nome = c.getString(c.getColumnIndex(UsuarioContrato.CHAVE_NOME))
        return Usuario(nome,id)
    }

}
