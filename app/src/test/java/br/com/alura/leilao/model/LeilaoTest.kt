package br.com.alura.leilao.model

import br.com.alura.leilao.builders.LeilaoBuilder
import br.com.alura.leilao.utils.Const
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class LeilaoTest {
    private val leilao = Leilao("Console");
    private val usuarioTeste1 = Usuario ("teste1")
    private val DELTA: Double = 0.0001

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
        assertEquals(200.0, maiorLance, DELTA)
    }

    @Test
    fun When_RecebeLancesOrdemCrescente_Expect_MaiorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, DELTA)
    }

    /*Testes getMenorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MenorLance(){
        leilao.propoe(Lance(usuarioTeste1,200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, DELTA)
    }

    @Test
    fun  When_RecebeLancesOrdemCrescente_Expect_MenorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, DELTA)
    }


    @Test
    fun When_RecebeTresLances_Expect_TresMaioresLances(){
        leilao.propoe(Lance(usuarioTeste1, 200.0))
        leilao.propoe(Lance(Usuario("Teste2"), 300.0))
        leilao.propoe(Lance(usuarioTeste1, 400.0))

        val maioresLances : List<Lance> = leilao.tresMaioresLances()

        assertEquals(3, maioresLances.size)
        assertEquals(400.0, maioresLances[0].valor, DELTA)
        assertEquals(300.0, maioresLances[1].valor, DELTA)
        assertEquals(200.0, maioresLances[2].valor, DELTA)
    }

    @Test
    fun When_RecebeNenhumLance_Expect_TresMaioresLances(){
        val tresMaioresLances = leilao.tresMaioresLances()
        assertEquals(0, tresMaioresLances.size)
    }

    @Test
    fun When_RecebeUmLance_Expect_TresMaioresLances(){
        leilao.propoe(Lance(usuarioTeste1, 200.0))

        val tresMaioresLances = leilao.tresMaioresLances()
        assertEquals(1,tresMaioresLances.size)
        assertEquals(200.0, tresMaioresLances[0].valor, DELTA)

    }

    @Test
    fun When_RecebeDoisLances_Expect_TresMaioresLances(){
        leilao.propoe(Lance(Usuario("Teste2"), 100.0))
        leilao.propoe(Lance(usuarioTeste1, 200.0))

        val tresMaioresLances = leilao.tresMaioresLances()

        assertEquals(2, tresMaioresLances.size)
        assertEquals(200.0, tresMaioresLances[0].valor, DELTA)
        assertEquals(100.0, tresMaioresLances[1].valor, DELTA)
    }


    @Test
    fun When_RecebeMaisDeTresLances_Expect_TresMaioresLances(){
        val usuarioTeste2 = Usuario("Teste2")
        leilao.propoe(Lance(usuarioTeste1, 100.0))
        leilao.propoe(Lance(usuarioTeste2, 300.0))
        leilao.propoe(Lance(usuarioTeste1, 500.0))
        leilao.propoe(Lance(usuarioTeste2, 600.0))

        val tresMaioresLances = leilao.tresMaioresLances()

        assertEquals(3, tresMaioresLances.size)
        assertEquals(600.0, tresMaioresLances[0].valor, DELTA)
        assertEquals(500.0, tresMaioresLances[1].valor, DELTA)
        assertEquals(300.0, tresMaioresLances[2].valor, DELTA)

        leilao.propoe(Lance(usuarioTeste1, 700.0))

        val tresMaioresLancesNovo = leilao.tresMaioresLances()

        assertEquals(3, tresMaioresLancesNovo.size)
        assertEquals(700.0, tresMaioresLancesNovo[0].valor, DELTA)
        assertEquals(600.0, tresMaioresLancesNovo[1].valor, DELTA)
        assertEquals(500.0, tresMaioresLancesNovo[2].valor, DELTA)
    }

    @Test
    fun When_RecebeNenhumLance_Expect_MaiorLanceZero(){
        val maiorLance = leilao.maiorLance
        assertEquals(0.0, maiorLance, DELTA)
    }

    @Test
    fun When_RecebeNenhumLance_Expect_MenorLanceZero(){
        val menorLance = leilao.menorLance

        assertEquals(0.0, menorLance, DELTA)
    }


    @Test
    fun When_RecebeLanceMenorQueMaior_Expect_Rejeitar(){
        leilao.propoe(Lance(usuarioTeste1, 500.0))
        try{
            leilao.propoe(Lance(Usuario("Teste2"), 300.0))
            fail("Era esperada uma RuntimeException")
        }catch (e : RuntimeException){
            assertEquals(Const.Exceptions.LANCE_MENOR_QUE_MAIOR_LANCE, e.message)
        }


    }

    @Test
    fun When_RecebeLanceDoMesmoUsuario_Expect_Reijeitar(){
        leilao.propoe(Lance(usuarioTeste1, 500.0))
        try {
            leilao.propoe(Lance(Usuario("teste1"), 600.0))
            fail("Era esperada uma RuntimeException")
        }catch (e: RuntimeException){
            assertEquals(Const.Exceptions.USUARIO_IGUAL_ULTIMO_LANCE, e.message)
        }

    }

    @Test
    fun When_RecebeCincoLancesDoMesmoUsuario_Expect_Rejeitar(){

        val usuarioTeste2 = Usuario("teste2")
        val leilao = LeilaoBuilder("console")
                .lance(usuarioTeste1, 100.0)
                .lance(usuarioTeste2, 120.0)
                .lance(usuarioTeste1, 130.0)
                .lance(usuarioTeste2, 140.0)
                .lance(usuarioTeste1, 150.0)
                .lance(usuarioTeste2, 160.0)
                .lance(usuarioTeste1, 170.0)
                .lance(usuarioTeste2, 180.0)
                .lance(usuarioTeste1, 190.0)
                .lance(usuarioTeste2, 200.0)
        try {
            leilao.lance(usuarioTeste1,210.0)
            fail("Era esperada uma RuntimeException")
        }catch (e : RuntimeException){
            assertEquals(Const.Exceptions.USUARIO_TEM_CINCO_LANCES, e.message)
        }


    }


}