package com.example.expobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvNumeros: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvNumeros = findViewById(R.id.tvNumeros)
    }

    fun novoJogo(view: View) {
        this.tvNumeros.text = Megasena.getInstance().joinToString(" | ")
    }
}