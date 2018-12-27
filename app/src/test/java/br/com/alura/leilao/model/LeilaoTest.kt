package br.com.alura.leilao.model

import org.junit.Test

import org.junit.Assert.*

class LeilaoTest {
    private val leilao = Leilao("Console");
    private val usuarioTeste1 = Usuario ("teste1")

    @Test
    fun When_RecebeDescricao_Expect_Descricao() {
        val descricaoDevolvida = leilao.descricao
        assertEquals("Console", descricaoDevolvida)
    }

    /*Testes getMaiorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MaiorLance(){
        leilao.propoe(Lance(usuarioTeste1,200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(200.0, maiorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemCrescente_Expect_MaiorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MaiorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,300.0))
        leilao.propoe(Lance(usuarioTeste2,200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, 0.0001 )
    }

    /*Testes getMenorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MenorLance(){
        leilao.propoe(Lance(usuarioTeste1,200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }

    @Test
    fun  When_RecebeLancesOrdemCrescente_Expect_MenorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MenorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,300.0))
        leilao.propoe(Lance(usuarioTeste2,200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }
}