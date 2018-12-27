package br.com.alura.leilao.model

import org.junit.Test

import org.junit.Assert.*

class LeilaoTest {

    @Test
    fun When_RecebeDescricao_Expect_Descricao() {
        // Criar cenário
        val leilao = Leilao("Console")
        // executar ação esperada
        val descricaoDevolvida = leilao.descricao
        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida)
    }

    /*Testes getMaiorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MaiorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(200.0, maiorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemCrescente_Expect_MaiorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        leilao.propoe(Lance(Usuario("Teste2"),300.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MaiorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste2"),300.0))
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, 0.0001 )
    }

    /*Testes getMenorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MenorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }

    @Test
    fun  When_RecebeLancesOrdemCrescente_Expect_MenorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        leilao.propoe(Lance(Usuario("Teste2"),300.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MenorLance(){
        val leilao = Leilao("Console")
        leilao.propoe(Lance(Usuario("Teste2"),300.0))
        leilao.propoe(Lance(Usuario("Teste"),200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, 0.0001 )
    }
}