package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class OutraActivity : AppCompatActivity() {
    private lateinit var btVolta : Button
    private lateinit var etMsg : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outra)

        this.btVolta = findViewById(R.id.btOutraVolta)
        this.etMsg = findViewById(R.id.etOutraMensagem)

        this.btVolta.setOnClickListener(onClickBotao())

        this.etMsg.setText(intent.getStringExtra("MENSAGEM"))
    }

    inner class onClickBotao : View.OnClickListener{
        override fun onClick(v: View?) {
            val it = Intent()
            val msg = this@OutraActivity.etMsg.text.toString()

            it.putExtra("MENSAGEM_VOLTA", msg)
            setResult(Activity.RESULT_OK, it)

            finish()
        }
    }
}
