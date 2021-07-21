package com.axweb.lojavirtual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.axweb.lojavirtual.R
import com.axweb.lojavirtual.TelaPrincipalActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_form_login.*

class FormLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        supportActionBar!!.hide()

        checkLoggedInUser()

        text_tela_cadastro.setOnClickListener {
            val intent = Intent(this, FormCadastroActivity::class.java)
            startActivity(intent)
        }

        bt_entrar.setOnClickListener {
            authenticateUser()
        }

    }

    private fun authenticateUser() {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {

            val snackbar = Snackbar.make(layout_login,"Coloque um email e uma senha",Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                .setAction("OK", View.OnClickListener {
                })
            snackbar.show()
        }else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
                if (it.isSuccessful) {
                    frameL.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({openMainScreen()},3000)
                }
            }.addOnFailureListener {
                val snackbar = Snackbar.make(layout_login,"Erro ao logar usuário",Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                    .setAction("OK", View.OnClickListener {
                    })
                snackbar.show()
            }
        }
    }
    private fun checkLoggedInUser() {
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            openMainScreen()
        }
    }

    private fun openMainScreen() {
        val intent = Intent(this, TelaPrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }
}