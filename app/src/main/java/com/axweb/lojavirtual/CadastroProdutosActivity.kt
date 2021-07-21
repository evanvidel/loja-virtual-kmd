package com.axweb.lojavirtual

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.axweb.lojavirtual.model.Dados
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_cadastro_produtos.*
import kotlinx.android.synthetic.main.activity_cadastro_produtos.view.*
import java.util.*

class CadastroProdutosActivity : AppCompatActivity() {

    private var selecionarUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produtos)

        bt_selecionar_foto.setOnClickListener {

            selectPhoto()
        }

        bt_cadastrar_produto.setOnClickListener {
            saveDataInFirebase()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            selecionarUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selecionarUri)
            foto_produto.setImageBitmap(bitmap)
            bt_selecionar_foto.alpha = 0f
        }
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }
    private fun saveDataInFirebase() {
        val nomeAquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
            "/imagens/${nomeAquivo}")

        selecionarUri?.let {
            referencia.putFile(it)
                .addOnSuccessListener {
                 referencia.downloadUrl.addOnSuccessListener {

                     val url = it.toString()
                     val nome = edit_nome.text.toString()
                     val preco = edit_preco.text.toString()
                     val uid = FirebaseAuth.getInstance().uid

                     val Produtos = Dados(url,nome,preco)

                     FirebaseFirestore.getInstance().collection("Produtos")
                         .add(Produtos)
                         .addOnSuccessListener {
                            Toast.makeText(this, "Produ cadastrado com sucesso",Toast.LENGTH_SHORT).show()
                         }
                 }.addOnFailureListener {

                 }
            }
        }
    }
}
