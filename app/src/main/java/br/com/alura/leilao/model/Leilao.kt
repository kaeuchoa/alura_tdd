package br.com.alura.leilao.model

import br.com.alura.leilao.exceptions.LanceMenorQueUltimoException
import br.com.alura.leilao.exceptions.UsuarioIgualUltimoLanceException
import br.com.alura.leilao.exceptions.UsuarioTemCincoLancesException
import java.io.Serializable
import java.util.*

class Leilao(val descricao: String) : Serializable {

    val id: Long = 0L
    private val lances: ArrayList<Lance> = ArrayList()
    val maiorLance: Double
        get() {
            if (lances.isEmpty())
                return 0.0

            return lances[0].valor
        }
    val menorLance: Double
        get() {
            if (lances.isEmpty())
                return 0.0

            return lances[lances.size - 1].valor
        }


    val quantidadeLances: Int
        get() {
            return this.lances.size
        }

    val tresMaioresLances: List<Lance>
        get() {
            val tamanhoMaximo = if (lances.size > 3) {
                3
            } else {
                lances.size
            }
            return lances.subList(0, tamanhoMaximo)
        }


    fun propoe(lance: Lance) {
        validaLance(lance)
        lances.add(lance)
        lances.sort()
    }

    private fun validaLance(lance: Lance) {
        val valorDoLance = lance.valor
        if (lanceMenorQueUltimoLance(valorDoLance))
            throw LanceMenorQueUltimoException()
        if (temLances()) {
            val usuarioNovo = lance.usuario
            if (usuarioIgualUltimoUsuario(usuarioNovo))
                throw UsuarioIgualUltimoLanceException()
            if (usuarioTemCincoLances(lance))
                throw UsuarioTemCincoLancesException()
        }
    }

    private fun temLances(): Boolean {
        return !lances.isEmpty()
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

    private fun usuarioIgualUltimoUsuario(novoUsuario: Usuario): Boolean {
        val ultimoUsuario = lances[0].usuario
        return ultimoUsuario.equals(novoUsuario)
    }

    private fun lanceMenorQueUltimoLance(valorDoLance: Double): Boolean {
        return maiorLance > valorDoLance
    }


}
