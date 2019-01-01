package br.com.alura.leilao.builders

import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.model.Usuario

class LeilaoBuilder(private val descricao: String){

    private val leilao = Leilao(descricao)

    fun lance(usuario: Usuario, valor: Double):LeilaoBuilder{
        leilao.propoe(Lance(usuario,valor))
        return this
    }

    fun build(): Leilao {
        return leilao
    }


}