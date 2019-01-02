package br.com.alura.leilao.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Usuario
import kotlinx.android.synthetic.main.form_usuario.view.*

class NovoUsuarioDialog(private val context: Context,
                        private val listener: UsuarioCriadoListener) {

    fun mostra() {
        val viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.form_usuario, null, false)
        val textInputNome : TextInputLayout = viewCriada.form_usuario_nome

        val campoNome = textInputNome.editText

        AlertDialog.Builder(context)
                .setTitle(TITULO)
                .setView(viewCriada)
                .setPositiveButton(DESCRICAO_BOTAO_POSITIVO,
                        criaNovoUsuarioListener(campoNome))
                .show()
    }

    private fun criaNovoUsuarioListener(campoNome: EditText?): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { _, _ ->
            val nome = campoNome!!.text.toString()
            val novoUsuario = Usuario(nome)
            listener.criado(novoUsuario)
        }
    }

    interface UsuarioCriadoListener {
        fun criado(usuario: Usuario)
    }

    companion object {

        private val TITULO = "Novo usuário"
        private val DESCRICAO_BOTAO_POSITIVO = "Adicionar"
    }

}
