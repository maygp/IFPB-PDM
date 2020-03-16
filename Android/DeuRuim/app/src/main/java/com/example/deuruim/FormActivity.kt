package com.example.deuruim

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.view.drawToBitmap

class FormActivity : AppCompatActivity() {

    private lateinit var etDescricao : EditText
    private lateinit var sbNota : SeekBar
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button
    private lateinit var btFoto: Button
    private lateinit var ivFoto : ImageView
    val FOTO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.etDescricao = findViewById(R.id.etDescricao)
        this.sbNota = findViewById(R.id.sbNota)
        this.btSalvar = findViewById(R.id.btSalvar)
        this.btCancelar = findViewById(R.id.btCancelar)
        this.btFoto = findViewById(R.id.btFoto)
        this.ivFoto = findViewById(R.id.ivFoto)

        this.btFoto.setOnClickListener { tirarFoto() }

        this.btSalvar.setOnClickListener{salvar()}

        this.btCancelar.setOnClickListener{
            finish()
        }
    }

    fun salvar(){
        val descricao = this.etDescricao.text.toString()
        val nota = this.sbNota.progress
        val foto = if (ivFoto.drawable != null)
            this.ivFoto.drawToBitmap()
        else null

        val evento = Evento(descricao, nota, foto)
        val intent = Intent()

        intent.putExtra("EVENTO", evento)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }

    fun tirarFoto(){
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == FOTO){
                val foto = data?.extras?.get("data") as Bitmap
                this.ivFoto.setImageBitmap(foto)

            }
        }
    }
}
