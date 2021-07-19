package com.axweb.lojavirtual.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axweb.lojavirtual.R

class FormCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar!!.hide()
    }
}