package com.example.arrocheonumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private lateinit var btVoltar : Button
    private lateinit var tvResultado : TextView
    private lateinit var tvResult2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        this.btVoltar = findViewById(R.id.btVoltar)
        this.tvResultado = findViewById(R.id.tvResult)
        this.tvResult2 = findViewById(R.id.tvResult2)

        btVoltar.setOnClickListener(OnClickVoltar())

        tvResultado.text = intent.getStringExtra("RESULTADO")
        tvResult2.text = intent.getStringExtra("MENSAGEM")
    }

   inner class OnClickVoltar : View.OnClickListener {
       override fun onClick(p0: View?) {
           finish()
       }
   }
}