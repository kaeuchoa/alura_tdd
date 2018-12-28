package br.com.alura.leilao.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alura.leilao.R
import br.com.alura.leilao.extensions.formataParaDinheiro
import br.com.alura.leilao.model.Leilao
import kotlinx.android.synthetic.main.activity_lances_leilao.*
import java.lang.StringBuilder

class LancesLeilaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lances_leilao)
        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra("leilao")) {
            val leilao = dadosRecebidos.getSerializableExtra("leilao") as Leilao
            lances_leilao_descricao.text = leilao.descricao
            lances_leilao_maior_lance.text = leilao.maiorLance.formataParaDinheiro()
            lances_leilao_menor_lance.text = leilao.menorLance.formataParaDinheiro()

            val tresMaioresLances = leilao.tresMaioresLances()
            val stringBuilder : StringBuilder = StringBuilder()
            for ( lance in tresMaioresLances){
                stringBuilder.append("${lance.valor.formataParaDinheiro()}\n" )
            }

            lances_leilao_maiores_lances.text = stringBuilder.toString()
        }
    }
}
