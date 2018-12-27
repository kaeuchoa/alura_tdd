package br.com.alura.leilao.model

import java.io.Serializable
import java.util.ArrayList

class Leilao(val descricao: String) : Serializable {
    private val lances: List<Lance>
    var maiorLance: Double = Double.NEGATIVE_INFINITY
    var menorLance: Double = Double.POSITIVE_INFINITY
    init {
        this.lances = ArrayList()
    }


    fun propoe(lance: Lance){
        val valorDoLance = lance.valor
        if(valorDoLance > maiorLance)
            maiorLance = valorDoLance


        if(valorDoLance < menorLance)
            menorLance = valorDoLance

    }

}
