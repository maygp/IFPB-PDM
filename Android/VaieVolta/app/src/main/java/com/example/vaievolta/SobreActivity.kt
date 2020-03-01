package com.example.vaievolta

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SobreActivity : AppCompatActivity() {
    private lateinit var imgSobre : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        this.imgSobre = findViewById(R.id.imgSobre)

        this.imgSobre.setOnClickListener {
            finish()
        }
    }
}