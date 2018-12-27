package br.com.alura.leilao.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter
import kotlinx.android.synthetic.main.activity_lista_leilao.*
import java.util.*

class ListaLeilaoActivity : AppCompatActivity() {

    private val rvLeiloes = lista_leilao_recyclerview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_leilao)
        val adapter = ListaLeilaoAdapter(this, leiloesDeExemplo())

        rvLeiloes.adapter = adapter
        adapter.setOnItemClickListener { leilao ->
            val vaiParaLancesLeilao = Intent(this, LancesLeilaoActivity::class.java)
            vaiParaLancesLeilao.putExtra("leilao", leilao)
            startActivity(vaiParaLancesLeilao)
        }
    }

    private fun leiloesDeExemplo(): List<Leilao> {
        return listOf(Leilao("Console"))
    }

}
