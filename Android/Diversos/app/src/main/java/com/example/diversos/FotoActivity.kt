package com.example.diversos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class FotoActivity : AppCompatActivity() {
    private lateinit var ivFoto : ImageView
    private lateinit var btFotoFechar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)

        this.ivFoto = findViewById(R.id.ivFoto)
        this.btFotoFechar = findViewById(R.id.btFotoFechar)

        this.ivFoto.setImageBitmap(intent.getParcelableExtra("FOTO"))

        btFotoFechar.setOnClickListener {
            finish()
        }

    }
}
