package br.com.alura.leilao.ui.dialog

import android.content.Context
import android.support.v7.app.AlertDialog

object AvisoDialogManager {

    private val MENSAGEM_PADRAO_BOTAO_POSITIVO = "Ok"
    private val MENSAGEM_AVISO_JA_DEU_CINCO_LANCES = "Usuário não pode dar mais de cinco lances no mesmo leilão"
    private val MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO = "O mesmo usuário do último lance não pode propror novos lances"
    private val MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE = "Lance precisa ser maior que o último lance"
    private val MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE = "Não foi possível enviar Lance"
    private val MENSAGEM_AVISO_VALOR_INVALIDO = "Valor inválido"

    fun mostraToastFalhaNoEnvio(context: Context) {
        mostraDialog(context, MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE)
    }

    fun mostraAvisoUsuarioJaDeuCincoLances(context: Context) {
        mostraDialog(context, MENSAGEM_AVISO_JA_DEU_CINCO_LANCES)
    }

    fun mostraAvisoLanceSeguidoDoMesmoUsuario(context: Context) {
        mostraDialog(context, MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO)
    }

    fun mostraAvisoLanceMenorQueUltimoLance(context: Context) {
        mostraDialog(context, MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE)
    }

    fun mostraAvisoValorInvalido(context: Context) {
        mostraDialog(context, MENSAGEM_AVISO_VALOR_INVALIDO)
    }

    private fun mostraDialog(context: Context, mensagem: String) {
        AlertDialog.Builder(context)
                .setMessage(mensagem)
                .setPositiveButton(MENSAGEM_PADRAO_BOTAO_POSITIVO, null)
                .show()
    }

}
