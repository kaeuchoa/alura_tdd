package br.com.alura.leilao.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.model.Usuario
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter
import kotlinx.android.synthetic.main.activity_lista_leilao.*

class ListaLeilaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_leilao)
        val adapter = ListaLeilaoAdapter(this, leiloesDeExemplo())

        lista_leilao_recyclerview.adapter = adapter
        adapter.setOnItemClickListener(object : ListaLeilaoAdapter.OnItemClickListener {
            override fun onItemClick(leilao: Leilao?) {
                val vaiParaLancesLeilao = Intent(this@ListaLeilaoActivity, LancesLeilaoActivity::class.java)
                vaiParaLancesLeilao.putExtra("leilao", leilao)
                startActivity(vaiParaLancesLeilao)
            }

        })

    }

    private fun leiloesDeExemplo(): List<Leilao> {
        val leilaoConsole = Leilao("Console")
        leilaoConsole.propoe(Lance(Usuario("Teste1"),200.0))
        leilaoConsole.propoe(Lance(Usuario("Teste2"),300.0))

        val leilaoCarro = Leilao("Carro")
        leilaoCarro.propoe(Lance(Usuario("Teste2"),300.0))
        leilaoCarro.propoe(Lance(Usuario("Teste1"),400.0))
        leilaoCarro.propoe(Lance(Usuario("Teste2"),600.0))
        leilaoCarro.propoe(Lance(Usuario("Teste1"),800.0))

        val leilaoComputador = Leilao("Computador")
        leilaoComputador.propoe(Lance(Usuario("Teste1"),100.0))

        return listOf(leilaoConsole, leilaoCarro, leilaoComputador)
    }
}


