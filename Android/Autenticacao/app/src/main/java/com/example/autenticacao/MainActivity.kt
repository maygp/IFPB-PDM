package com.example.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etUser : EditText
    private lateinit var etPassword : EditText
    private lateinit var btCancel : Button
    private lateinit var btOk : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etUser = findViewById(R.id.etUser)
        this.etPassword = findViewById(R.id.etPassword)
        this.btCancel = findViewById(R.id.btCancel)
        this.btOk = findViewById(R.id.btOk)

        this.btCancel.setOnClickListener {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            Log.i("APP_AUTH", "Canceled by User")
            this.etUser.text.clear()
            this.etPassword.text.clear()
        }

        this.btOk.setOnClickListener(okBtnClick())
    }

    inner class okBtnClick : View.OnClickListener {
        override fun onClick(v: View?) {
            var user = this@MainActivity.etUser.text.toString()
            var pass = this@MainActivity.etPassword.text.toString()
            val intent = Intent(this@MainActivity, LoggedIn::class.java)

            if(user == "admin" && pass == "123abc"){
                Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                Log.i("APP_AUTH", "User logged in")
            }else {
                Toast.makeText(this@MainActivity, "Incorrect Password/User", Toast.LENGTH_SHORT).show()
                Log.i("APP_AUTH", "Incorrect password/user")
            }
        }
    }
}
