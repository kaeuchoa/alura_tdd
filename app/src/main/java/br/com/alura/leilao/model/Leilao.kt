package br.com.alura.leilao.model

import java.io.Serializable
import java.util.*

class Leilao(val descricao: String) : Serializable {
    private val lances: ArrayList<Lance> = ArrayList()
    var maiorLance: Double = Double.NEGATIVE_INFINITY
    var menorLance: Double = Double.POSITIVE_INFINITY


    fun propoe(lance: Lance){
        val valorDoLance = lance.valor
        lances.add(lance)
        lances.sort()
        calculaMaiorLance(valorDoLance)
        calculaMenorLance(valorDoLance)
    }

    private fun calculaMenorLance(valorDoLance: Double) {
        if (valorDoLance < menorLance)
            menorLance = valorDoLance
    }

    private fun calculaMaiorLance(valorDoLance: Double) {
        if (valorDoLance > maiorLance)
            maiorLance = valorDoLance
    }

    fun tresMaioresLances(): List<Lance> {
        val tamanhoMaximo = if(lances.size > 3){
            3
        }else{
            lances.size
        }
        return lances.subList(0, tamanhoMaximo)
    }

}
