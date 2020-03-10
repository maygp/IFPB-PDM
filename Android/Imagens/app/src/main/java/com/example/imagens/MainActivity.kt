package com.example.imagens

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.*
import androidx.fragment.app.DialogFragment
import java.net.URL
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var btCamera: Button
    private lateinit var btArquivo: Button
    private lateinit var btDownload: Button
    private lateinit var ivImagem: ImageView
    private lateinit var rgImagens : RadioGroup
    private lateinit var ldpi: RadioButton
    private lateinit var mdpi: RadioButton
    private lateinit var xxxhdpi: RadioButton

    val CAMERA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCamera = findViewById(R.id.btCamera)
        this.btArquivo = findViewById(R.id.btArquivo)
        this.btDownload = findViewById(R.id.btDownload)
        this.rgImagens = findViewById(R.id.rgImagens)
        // this.rgImagens.setOnCheckedChangeListener(ChangeRadioGroup())

        this.xxxhdpi = findViewById(R.id.rbxxxhdpi)

        this.ivImagem = findViewById(R.id.ivImagem)

        this.btCamera.setOnClickListener{ camera() }
        this.btArquivo.setOnClickListener{ xml() }
        this.btDownload.setOnClickListener{ criaDialog() }

    }

//    inner class ChangeRadioGroup : RadioGroup.OnCheckedChangeListener{
//        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//            Toast.makeText(this@MainActivity, checkedId.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                val imagem = data?.extras?.get("data") as Bitmap
                this.ivImagem.setImageBitmap(imagem)
            }
        }
    }

    fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    fun xml() {
        this.ivImagem.setImageResource(R.drawable.super_mario)
    }
    //lambda
    val positiveBtClick = {_: DialogInterface, _: Int ->
        if(xxxhdpi.isChecked){
            download()
        }else{
            Toast.makeText(this,"Selecione xxxhdpi", Toast.LENGTH_SHORT).show()
        }
    }

    fun downloadDaImagem(url: String): Bitmap {
        URL(url).openStream().use {
            val imagem = BitmapFactory.decodeStream(it)
            return imagem
        }
    }

    fun download() {
        val handler = Handler()
        val url = "http://www.valeria.eti.br/sm/sm_xxxhdpi.png"

        Thread {
            val imagem = this.downloadDaImagem(url)

            handler.post {
                this.ivImagem.setImageBitmap(imagem)
            }
        }.start()
    }

    fun criaDialog() {
        var janelinha = AlertDialog.Builder(this)
        var view = this.layoutInflater.inflate(R.layout.dialog, null)

        janelinha.setTitle("Super Mario")
        janelinha.setIcon(R.mipmap.ic_launcher)
        janelinha.setView(view)
        janelinha.setPositiveButton("OK", DialogInterface.OnClickListener(positiveBtClick))
        janelinha.setNegativeButton("Cancelar", null)
        janelinha.create().show()
    }

}
