package br.com.alura.leilao.model

import java.io.Serializable

class Lance(val usuario: Usuario, val valor: Double) : Serializable, Comparable<Lance> {
    override fun compareTo(lance: Lance): Int {
        if (valor > lance.valor)
            return -1
        if (valor < lance.valor)
            return 1
        return 0
    }
}
