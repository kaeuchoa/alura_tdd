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
        if (lanceNaoValido(lance)) return

        val valorDoLance = lance.valor
        lances.add(lance)
        if (configuraMaiorEMenorLance(valorDoLance)) return
        lances.sort()
        calculaMaiorLance(valorDoLance)
        calculaMenorLance(valorDoLance)
    }

    private fun configuraMaiorEMenorLance(valorDoLance: Double): Boolean {
        if (lances.size == 1) {
            maiorLance = valorDoLance
            menorLance = valorDoLance
            return true
        }
        return false
    }

    private fun lanceNaoValido(lance: Lance): Boolean {
        val valorDoLance = lance.valor
        if (lanceMenorQueUltimoLance(valorDoLance)) return true
        val temLances = !lances.isEmpty()
        if (temLances) {
            if (usuarioIgualUltimoUsuario(lance)) return true
            if (usuarioTemCincoLances(lance)) return true
        }
        return false
    }

    private fun usuarioTemCincoLances(lance: Lance): Boolean {
        var contadorLances = 0
        for (l in lances) {
            val usuarioAtual = l.usuario
            if (usuarioAtual.equals(lance.usuario))
                contadorLances++

            if (contadorLances == 5)
                return true
        }
        return false
    }

    private fun usuarioIgualUltimoUsuario(lance: Lance): Boolean {
        if (lance.usuario.equals(lances[0].usuario)) {
            return true
        }
        return false
    }

    private fun lanceMenorQueUltimoLance(valorDoLance: Double): Boolean {
        if (maiorLance > valorDoLance)
            return true
        return false
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
