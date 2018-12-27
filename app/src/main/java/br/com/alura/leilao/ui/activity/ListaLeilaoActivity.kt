package br.com.alura.leilao.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Leilao
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
        return listOf(Leilao("Console"))
    }
}


