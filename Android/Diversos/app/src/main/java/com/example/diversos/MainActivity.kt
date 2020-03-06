package com.example.diversos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btHtml : Button
    private lateinit var btDiscar : Button
    private lateinit var btLigar : Button
    private lateinit var btCompartilhar : Button
    private lateinit var btEmail : Button
    private lateinit var btPonto : Button
    private lateinit var btRota : Button
    private lateinit var btSms : Button
    private lateinit var btYoutube : Button
    private lateinit var btFoto : Button
    val REQUEST_FOTO = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btHtml = findViewById(R.id.btHtml)
        this.btDiscar = findViewById(R.id.btDiscar)
        this.btLigar = findViewById(R.id.btLigar)
        this.btCompartilhar = findViewById(R.id.btCompartilhar)
        this.btEmail = findViewById(R.id.btEmail)
        this.btPonto = findViewById(R.id.btPonto)
        this.btRota = findViewById(R.id.btRota)
        this.btSms = findViewById(R.id.btSms)
        this.btYoutube = findViewById(R.id.btYoutube)
        this.btFoto = findViewById(R.id.btFoto)

        this.btHtml.setOnClickListener{html()}
        this.btDiscar.setOnClickListener{discar()}
        this.btLigar.setOnClickListener{ligar()}
        this.btCompartilhar.setOnClickListener{compartilhar()}
        this.btEmail.setOnClickListener{email()}
        this.btPonto.setOnClickListener{ponto()}
        this.btRota.setOnClickListener{rota()}
        this.btSms.setOnClickListener{sms()}
        this.btYoutube.setOnClickListener{youtube()}
        this.btFoto.setOnClickListener{foto()}
    }

    fun html() {
        val uri = Uri.parse("http://ifpb.edu.br")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun discar() {
        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_DIAL, uri)

        startActivity(intent)
    }


    fun ligar() {
        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_CALL, uri)
        val call = Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED

        if (ContextCompat.checkSelfPermission(this, call) == granted){
            startActivity(intent)
        }else{
            Toast.makeText(this, "Nao pode ligar", Toast.LENGTH_SHORT).show()
        }

    }

    fun compartilhar(){
        val intent = Intent(Intent.ACTION_SEND)

        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
        // intent.setPackage("com.whatsapp")

        if (intent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(intent, "Compartilhar"))
        }

    }

    fun email() {

        val uri = Uri.parse("mailto:valeria.cavalcanti@ifpb.edu.br")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        intent.putExtra(Intent.EXTRA_SUBJECT, "Assunto")

        intent.putExtra(Intent.EXTRA_TEXT, "Texto")

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun ponto() {
        val uri = Uri.parse("geo:-7.1356496,-34.8760932")

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun rota() {
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"
        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun sms() {
        val uri = Uri.parse("sms:36121392")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        intent.putExtra("sms_body", "Mensagem")

        startActivity(intent)
    }

    fun youtube() {
        val uri = Uri.parse("vnd.youtube://dglqGGyWbVo")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun foto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_FOTO){
                var foto = data?.extras?.get("data") as Bitmap
                var it = Intent(this@MainActivity, FotoActivity::class.java)
                it.putExtra("FOTO", foto)
                startActivity(it)
            }
        }
    }
}