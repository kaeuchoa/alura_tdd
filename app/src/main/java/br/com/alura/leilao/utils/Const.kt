package br.com.alura.leilao.utils

class Const {

    object Exceptions{
        const val LANCE_MENOR_QUE_MAIOR_LANCE = "Lance menor que maior lance"
        const val USUARIO_IGUAL_ULTIMO_LANCE: String = "Usuario do lance atual é igual ao do ultimo lance"
        const val USUARIO_TEM_CINCO_LANCES: String = "O usuario já tem 5 lances"
    }
}