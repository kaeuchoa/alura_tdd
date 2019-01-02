package br.com.alura.leilao.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.alura.leilao.R
import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient
import br.com.alura.leilao.api.retrofit.client.RespostaListener
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.model.Usuario
import br.com.alura.leilao.ui.activity.LeilaoConstantes.Companion.CHAVE_LEILAO
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter
import kotlinx.android.synthetic.main.activity_lista_leilao.*

class ListaLeilaoActivity : AppCompatActivity() {

    private val client = LeilaoWebClient()
    private val adapter: ListaLeilaoAdapter by lazy {
        ListaLeilaoAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_leilao)
        supportActionBar?.title = getString(R.string.app_name)
        configuraListaLeiloes()
    }

    private fun configuraListaLeiloes(){
        configuraAdapter()
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val rvLeiloes = lista_leilao_recyclerview
        rvLeiloes.adapter = adapter
    }


    private fun configuraAdapter() {
        adapter.setOnItemClickListener(object : ListaLeilaoAdapter.OnItemClickListener {
            override fun onItemClick(leilao: Leilao?) {
                if (leilao != null) {
                    vaiParaTelaDeLances(leilao)
                }
            }

        })
    }

    private fun vaiParaTelaDeLances(leilao: Leilao) {
        val intent = Intent(ListaLeilaoActivity@ this, LancesLeilaoActivity::class.java)
        intent.putExtra(CHAVE_LEILAO, leilao)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        client.todos(object : RespostaListener<List<Leilao>> {
            override fun sucesso(leiloes: List<Leilao>) {
                adapter.atualiza(leiloes)
            }

            override fun falha(mensagem: String) {
                Toast.makeText(this@ListaLeilaoActivity,
                        getString(R.string.msg_falha_ao_carregar),
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lista_leilao_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        when(itemId){
            R.id.lista_leilao_menu_usuarios -> {
                startActivity(Intent(this, ListaUsuarioActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


