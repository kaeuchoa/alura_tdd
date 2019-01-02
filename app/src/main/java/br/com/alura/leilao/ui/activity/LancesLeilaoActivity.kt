package br.com.alura.leilao.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import br.com.alura.leilao.R
import br.com.alura.leilao.api.EnviadorDeLance
import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient
import br.com.alura.leilao.database.dao.UsuarioDAO
import br.com.alura.leilao.extensions.formataParaDinheiro
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.ui.activity.LeilaoConstantes.Companion.CHAVE_LEILAO
import br.com.alura.leilao.ui.dialog.NovoLanceDialog
import kotlinx.android.synthetic.main.activity_lances_leilao.*
import java.lang.StringBuilder

class LancesLeilaoActivity : AppCompatActivity() {

    private val usuarioDao: UsuarioDAO by lazy {
        UsuarioDAO(this)
    }

    private val txtDescricao: TextView by lazy {
        lances_leilao_descricao
    }

    private val txtMaiorLance: TextView by lazy {
        lances_leilao_maior_lance
    }

    private val txtMenorLance: TextView by lazy {
        lances_leilao_menor_lance
    }

    private val txtMaioresLances: TextView by lazy {
        lances_leilao_maiores_lances
    }

    private lateinit var leilaoRecebido: Leilao
    private val client = LeilaoWebClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lances_leilao)
        supportActionBar?.title = getString(R.string.titulo_lances_leilao)
        carregaLeilaoRecebido()

    }

    private fun carregaLeilaoRecebido() {
        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra(CHAVE_LEILAO)) {
            leilaoRecebido = dadosRecebidos.getSerializableExtra("leilao") as Leilao
            apresentaDados()
            configuraFAB()
        } else {
            finish()
        }
    }

    private fun configuraFAB() {
        lances_leilao_fab_adiciona.setOnClickListener {
            mostraDialogNovoLance()
        }
    }

    private fun mostraDialogNovoLance() {
        val dialog = NovoLanceDialog(this, object : NovoLanceDialog.LanceCriadoListener {
            override fun lanceCriado(lance: Lance) {
                enviaLance(lance)
            }
        }, usuarioDao)
        dialog.mostra()
    }

    private fun enviaLance(lance: Lance) {
        val enviador = EnviadorDeLance(client, lanceProcessadoListener(),
                this)
        enviador.envia(leilaoRecebido, lance)
    }

    private fun lanceProcessadoListener(): EnviadorDeLance.LanceProcessadoListener {
        return object : EnviadorDeLance.LanceProcessadoListener {
            override fun processado(leilao: Leilao) {
                leilaoRecebido = leilao
                apresentaDados()
            }
        }
    }

    private fun apresentaDados() {
        adicionaDescricao(leilaoRecebido)
        adicionaMaiorLance(leilaoRecebido)
        adicionaMenorLance(leilaoRecebido)
        adicionaMaioresLances(leilaoRecebido)
    }

    private fun adicionaMaioresLances(leilao: Leilao) {
        val stringBuilder = StringBuilder()
        for (lance in leilao.tresMaioresLances) {
            stringBuilder.append("${lance.valor.formataParaDinheiro()} - ${lance.usuario}\n")
        }

        txtMaioresLances.text = stringBuilder.toString()
    }

    private fun adicionaMenorLance(leilao: Leilao) {
        txtMenorLance.text = leilao.menorLance.formataParaDinheiro()
    }

    private fun adicionaMaiorLance(leilao: Leilao) {
        txtMaiorLance.text = leilao.maiorLance.formataParaDinheiro()
    }

    private fun adicionaDescricao(leilao: Leilao) {
        txtDescricao.text = leilao.descricao
    }
}
