package br.com.alura.leilao.model

import java.io.Serializable
import java.util.ArrayList

class Leilao(val descricao: String) : Serializable {
    private val lances: List<Lance>

    init {
        this.lances = ArrayList()
    }

}
