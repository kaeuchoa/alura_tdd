package br.com.alura.leilao.api

import android.content.Context
import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient
import br.com.alura.leilao.api.retrofit.client.RespostaListener
import br.com.alura.leilao.exceptions.LanceMenorQueUltimoException
import br.com.alura.leilao.exceptions.UsuarioIgualUltimoLanceException
import br.com.alura.leilao.exceptions.UsuarioTemCincoLancesException
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.ui.dialog.AvisoDialogManager.mostraAvisoLanceMenorQueUltimoLance
import br.com.alura.leilao.ui.dialog.AvisoDialogManager.mostraAvisoLanceSeguidoDoMesmoUsuario
import br.com.alura.leilao.ui.dialog.AvisoDialogManager.mostraAvisoUsuarioJaDeuCincoLances
import br.com.alura.leilao.ui.dialog.AvisoDialogManager.mostraToastFalhaNoEnvio

class EnviadorDeLance(private val client: LeilaoWebClient,
                      private val listener: LanceProcessadoListener,
                      private val context: Context) {

    fun envia(leilao: Leilao, lance: Lance) {
        try {
            leilao.propoe(lance)
            client.propoe(lance, leilao.id, object : RespostaListener<Void> {
                override fun sucesso(resposta: Void) {
                    listener.processado(leilao)
                }

                override fun falha(mensagem: String) {
                    mostraToastFalhaNoEnvio(context)
                }
            })
        } catch (exception: LanceMenorQueUltimoException) {
            mostraAvisoLanceMenorQueUltimoLance(context)
        } catch (exception: UsuarioIgualUltimoLanceException) {
            mostraAvisoLanceSeguidoDoMesmoUsuario(context)
        } catch (exception: UsuarioTemCincoLancesException) {
            mostraAvisoUsuarioJaDeuCincoLances(context)
        }

    }

    interface LanceProcessadoListener {
        fun processado(leilao: Leilao)
    }

}
