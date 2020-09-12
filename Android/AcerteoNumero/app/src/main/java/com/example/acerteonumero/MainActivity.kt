package com.example.acerteonumero

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvDica1 : TextView
    private lateinit var tvDica2 : TextView
    private lateinit var tvDica3 : TextView
    private lateinit var etResposta : EditText
    private lateinit var btEnviar : Button
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvDica1 = findViewById(R.id.tvDica1)
        this.tvDica2 = findViewById(R.id.tvDica2)
        this.tvDica3 = findViewById(R.id.tvDica3)
        this.etResposta = findViewById(R.id.etResposta)
        this.btEnviar = findViewById(R.id.btEnviar)

        btEnviar.setOnClickListener{verifyAnswer()}

        tips()
    }

    /** Gera um número aleatório **/
    fun generaterandom(): Int{
        return Random.nextInt(1, 100)
    }

    /** Verifica se o número é par ou ímpar: true se for par, false se for ímpar **/
    fun checkEvenOdd(n: Int): Boolean{
        return (n%2 == 0)
    }

    /** Conta a quantidade de divisores **/
    fun numberOfDivisors(n: Int): Int{
        var qt = 0
        var i = 1
        while (i <= n){
            if(n%i == 0)
                qt++
            i++
        }
        return qt
    }

    /** Lista os divisores **/
    fun listOfDivisors(n: Int): List<Int>{
        var list = mutableListOf<Int>()
        for (i in 1..10){
            if(n%i == 0)
                list.add(i)
        }
        return list
    }

    /** Exibe as dicas na tela **/
    fun tips(){
        this.number = generaterandom()
        Log.i("APP_ACERTE", number.toString())

        this.tvDica1.text = "1: Os divisores (entre 1 e 10) do número são: " + listOfDivisors(number).joinToString(", ")

        if(checkEvenOdd(number))
            this.tvDica2.text = "2: O número é par! "
        else
            this.tvDica2.text = "2: O número é ímpar!"

        this.tvDica3.text = "3: A quantidade total de divisores é: " + numberOfDivisors(number).toString()
    }

   /** Verifica a resposta e exibe o resultado **/
    fun verifyAnswer() {
       if (etResposta.text.toString() == "") {
           Toast.makeText(this, "Insira um valor entre 1 e 100", Toast.LENGTH_SHORT).show()
       } else {
           var hunch = etResposta.text.toString().toInt()
           if (this.number == hunch)
               resultOk()
           else
               resultFail()
       }
       etResposta.text.clear()
   }

    /** Caixa de diálogo caso a resposta esteja correta **/
    fun resultOk(){
        val window = AlertDialog.Builder(this)
        window.setTitle("Parabéns!")
        window.setMessage("Você acertou! :)")
        window.setPositiveButton("OK") { _, _ -> tips() }
        window.create().show()
    }

    /** Caixa de diálogo caso a resposta esteja errada **/
    fun resultFail(){
        val window = AlertDialog.Builder(this)
        window.setTitle("Não foi dessa vez!")
        window.setMessage("Você errou :( A resposta era $number")
        window.setPositiveButton("OK") { _, _ -> tips() }
        window.create().show()
    }

    override fun onRestart() {
        super.onRestart()
        tips()
    }
}