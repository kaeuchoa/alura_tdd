package br.com.alura.leilao.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.leilao.R
import br.com.alura.leilao.database.dao.UsuarioDAO
import br.com.alura.leilao.model.Usuario
import br.com.alura.leilao.ui.AtualizadorDeUsuario
import br.com.alura.leilao.ui.dialog.NovoUsuarioDialog
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter
import kotlinx.android.synthetic.main.activity_lista_usuario.*

class ListaUsuarioActivity : AppCompatActivity() {

    private val usuarioDAO: UsuarioDAO by lazy {
        UsuarioDAO(this)
    }

    private val adapter: ListaUsuarioAdapter by lazy {
        ListaUsuarioAdapter(this)
    }

    private val rvListaUsuarios by lazy {
        lista_usuario_recyclerview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuario)
        supportActionBar?.title = getString(R.string.titulo_lista_usuarios)
        configuraRecyclerView()
        configuraFAB()

    }

    private fun configuraRecyclerView() {

        rvListaUsuarios.adapter = adapter
        adapter.adiciona(usuarioDAO.todos())
    }

    private fun configuraFAB() {
        lista_usuario_fab_adiciona.setOnClickListener {
            mostraDialogAdicionaNovoUsuario()
        }
    }

    private fun mostraDialogAdicionaNovoUsuario() {
        val dialog = NovoUsuarioDialog(
                this,
                object : NovoUsuarioDialog.UsuarioCriadoListener {
                    override fun criado(usuario: Usuario) {
                        AtualizadorDeUsuario(
                                usuarioDAO,
                                adapter,
                                rvListaUsuarios)
                                .salva(usuario)
                    }
                })
        dialog.mostra()
    }
}
