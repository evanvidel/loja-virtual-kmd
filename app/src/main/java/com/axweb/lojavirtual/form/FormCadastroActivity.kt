package com.axweb.lojavirtual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.axweb.lojavirtual.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_form_cadastro.*

class FormCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar!!.hide()

        bt_cadastrar.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {

            val snackbar = Snackbar.make(layout_cadastro, "Coloque o seu Email e sua Senha!",Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
                })
            snackbar.show()

        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val snackbar = Snackbar.make(layout_cadastro, "Cadastro realizado com sucesso",Snackbar.LENGTH_INDEFINITE)
                            .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
                                backFormLogin()
                            })
                        snackbar.show()
                    }
                }.addOnFailureListener {
                    val snackbar = Snackbar.make(layout_cadastro, "Erro ao cadastrtar usuário",Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
                        })
                    snackbar.show()
            }
        }
    }

    private fun backFormLogin() {
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}