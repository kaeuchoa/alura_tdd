package br.com.alura.leilao.model

import java.io.Serializable
import java.util.*

class Leilao(val descricao: String) : Serializable {
    private val lances: ArrayList<Lance> = ArrayList()
    var maiorLance: Double = 0.0
    var menorLance: Double = 0.0
    val quantidadeLances: Int
        get() {
            return this.lances.size
        }


    fun propoe(lance: Lance) {
        val valorDoLance = lance.valor
        if(maiorLance > valorDoLance)
            return

        lances.add(lance)
        if (lances.size == 1) {
            maiorLance = valorDoLance
            menorLance = valorDoLance
            return
        }
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
        val tamanhoMaximo = if (lances.size > 3) {
            3
        } else {
            lances.size
        }
        return lances.subList(0, tamanhoMaximo)
    }

}
