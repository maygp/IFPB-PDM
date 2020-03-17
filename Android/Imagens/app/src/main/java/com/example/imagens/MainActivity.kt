package com.example.imagens

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var btCamera: Button
    private lateinit var btArquivo: Button
    private lateinit var btDownload: Button
    private lateinit var ivImagem: ImageView
    val CAMERA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCamera = findViewById(R.id.btCamera)
        this.btArquivo = findViewById(R.id.btArquivo)
        this.btDownload = findViewById(R.id.btDownload)
        this.ivImagem = findViewById(R.id.ivImagem)

        this.btCamera.setOnClickListener { camera() }
        this.btArquivo.setOnClickListener { xml() }
        this.btDownload.setOnClickListener { download() }
    }


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

    fun downloadDaImagem(url: String): Bitmap {
        URL(url).openStream().use {
            val imagem = BitmapFactory.decodeStream(it)
            return imagem
        }
    }

    fun download() {
        val handler = Handler()
        val imagens = arrayOf("ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi")
        var opcao = 0

        val janela = AlertDialog.Builder(this@MainActivity)
        janela.setTitle("Imagens")
        janela.setNegativeButton("Cancelar", null)
        janela.setPositiveButton("OK") { _, _ ->
            var url = ""
            when (opcao) {
                0 -> url += "http://www.valeria.eti.br/sm/sm_ldpi.png"
                1 -> url += "http://www.valeria.eti.br/sm/sm_mdpi.png"
                2 -> url += "http://www.valeria.eti.br/sm/sm_hdpi.png"
                3 -> url += "http://www.valeria.eti.br/sm/sm_xhdpi.png"
                4 -> url += "http://www.valeria.eti.br/sm/sm_xxhdpi.png"
                5 -> url += "http://www.valeria.eti.br/sm/sm_xxxhdpi.png"
            }

            Thread {
                val imagem = this.downloadDaImagem(url)

                handler.post {
                    this.ivImagem.setImageBitmap(imagem)
                }
            }.start()
        }
        janela.setSingleChoiceItems(imagens, 0) { _, i ->
            opcao = i }
        janela.create().show()
    }
}