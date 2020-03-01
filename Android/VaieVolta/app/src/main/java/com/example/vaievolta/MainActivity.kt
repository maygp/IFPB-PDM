package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btVai : Button
    private lateinit var etMsg : EditText
    private lateinit var btSobre : Button
    val OUTRA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btVai = findViewById(R.id.btMainVai)
        this.etMsg = findViewById(R.id.etMainMensagem)
        this.btSobre = findViewById(R.id.btSobre)

        this.btVai.setOnClickListener(onClickBotao())

        this.btSobre.setOnClickListener {
            val intent = Intent(this@MainActivity, SobreActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OUTRA){
                val msg = data?.getStringExtra("MENSAGEM_VOLTA")
                this.etMsg.setText(msg)
            } else {
                Toast.makeText(this, "voltou pelo dispositivo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class onClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, OutraActivity::class.java)
            val msg = this@MainActivity.etMsg.text.toString()

            it.putExtra("MENSAGEM", msg)
            //startActivity(it)
            startActivityForResult(it, OUTRA)
        }
    }
}
