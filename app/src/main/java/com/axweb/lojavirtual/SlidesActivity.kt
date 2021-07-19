package com.axweb.lojavirtual

import android.content.Intent
import android.os.Bundle
import com.axweb.lojavirtual.form.FormLoginActivity
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide

class SlidesActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_slides)

        isButtonBackVisible = false
        isButtonNextVisible = false


        addSlide(

            SimpleSlide.Builder()
                .background(R.color.purple)
                .image(R.drawable.drawer)
                .backgroundDark(R.color.white)
                .title("Loja Online de Calçados")
                .description("Entre e confira as promoções que preparamos para você!")
                .build()
        )

        addSlide(

            SimpleSlide.Builder()
                .background(R.color.blue_green)
                .title("Cria uma conta Grátis")
                .canGoBackward(false)
                .description("Cadastre agora mesmo! E venha conhecer nossos produtos.")
                .build()
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}