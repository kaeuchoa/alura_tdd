package br.com.alura.leilao.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import br.com.alura.leilao.R
import br.com.alura.leilao.database.dao.UsuarioDAO
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Usuario
import br.com.alura.leilao.ui.activity.ListaUsuarioActivity
import br.com.alura.leilao.ui.dialog.AvisoDialogManager.mostraAvisoValorInvalido
import kotlinx.android.synthetic.main.form_lance.view.*

class NovoLanceDialog(private val context: Context,
                      private val listener: LanceCriadoListener,
                      private val dao: UsuarioDAO) {

    fun mostra() {
        val usuarios = dao.todos()
        if (naoTemUsuariosCadastrados(usuarios)) return
        configuraView(usuarios)
    }

    private fun naoTemUsuariosCadastrados(usuarios: List<Usuario>): Boolean {
        if (usuarios.isEmpty()) {
            mostraDialogUsuarioNaoCadastrado()
            return true
        }
        return false
    }

    private fun mostraDialogUsuarioNaoCadastrado() {
        AlertDialog.Builder(context)
                .setTitle(USUARIOS_NAO_ENCONTRADOS)
                .setMessage(MENSAGEM_NAO_EXISTE_USUARIOS_CADASTRADOS)
                .setPositiveButton(CADASTRAR_USUARIO) { dialogInterface, i ->
                    val vaiParaListaDeUsuarios = Intent(context, ListaUsuarioActivity::class.java)
                    context.startActivity(vaiParaListaDeUsuarios)
                }.show()
    }

    private fun configuraView(usuarios: List<Usuario>) {
        val viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.form_lance, null, false)

        val campoUsuarios = viewCriada.form_lance_usuario
        val textInputValor = viewCriada.form_lance_valor

        configuraSpinnerUsuarios(usuarios, campoUsuarios)
        val campoValor = textInputValor.editText
        mostraDialogPropoeLance(viewCriada, campoUsuarios, campoValor)
    }

    private fun mostraDialogPropoeLance(viewCriada: View, campoUsuarios: Spinner, campoValor: EditText?) {
        AlertDialog.Builder(context)
                .setTitle(TITULO)
                .setView(viewCriada)
                .setPositiveButton(DESCRICAO_BOTAO_POSITIVO,
                        criaNovoLanceListener(campoValor, campoUsuarios))
                .setNegativeButton(DESCRICAO_BOTAO_NEGATIVO, null)
                .show()
    }

    private fun criaNovoLanceListener(
            campoValor: EditText?, campoUsuarios: Spinner): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialogInterface, i ->
            val valorEmTexto = campoValor!!.text.toString()
            val usuario = campoUsuarios.selectedItem as Usuario
            try {
                val valor = java.lang.Double.parseDouble(valorEmTexto)
                val novoLance = Lance(usuario, valor)
                listener.lanceCriado(novoLance)
            } catch (e: NumberFormatException) {
                mostraAvisoValorInvalido(context)
            }
        }
    }

    private fun configuraSpinnerUsuarios(usuarios: List<Usuario>,
                                         usuariosDisponiveis: Spinner) {
        val adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                usuarios)
        usuariosDisponiveis.adapter = adapter
    }

    interface LanceCriadoListener {
        fun lanceCriado(lance: Lance)
    }

    companion object {

        private val TITULO = "Novo lance"
        private val DESCRICAO_BOTAO_POSITIVO = "Propor"
        private val DESCRICAO_BOTAO_NEGATIVO = "Cancelar"
        private val USUARIOS_NAO_ENCONTRADOS = "Usuários não encontrados"
        private val MENSAGEM_NAO_EXISTE_USUARIOS_CADASTRADOS = "Não existe usuários cadastrados! Cadastre um usuário para propor o lance."
        private val CADASTRAR_USUARIO = "Cadastrar usuário"
    }


}