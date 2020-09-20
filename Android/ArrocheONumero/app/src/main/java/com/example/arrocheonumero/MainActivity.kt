package com.example.arrocheonumero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvIntervalo : TextView
    private lateinit var etResposta : EditText
    private lateinit var btEnviar : Button
    private var min = 1
    private var max = 100
    private var num = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvIntervalo = findViewById(R.id.tvIntervalo)
        this.etResposta = findViewById(R.id.etResposta)
        this.btEnviar = findViewById(R.id.btEnviar)

        this.btEnviar.setOnClickListener(OnClickBt())
        num = generateRandom()
        Log.i("APP_ARROCHA", num.toString())
    }

    /** Gera um número inteiro aleatório **/
    private fun generateRandom(): Int {
       return Random.nextInt(2, 99)
    }

    inner class OnClickBt : View.OnClickListener{
        override fun onClick(p0: View?) {
            val it = Intent(this@MainActivity, ResultActivity::class.java)

            /** Verifica se o EditText está vazio **/
            if (etResposta.text.toString() == "") {
                Toast.makeText(this@MainActivity, "Informe um valor dentro do intervalo mostrado!", Toast.LENGTH_SHORT).show()
            } else {
                var chute = etResposta.text.toString().toInt()

                /** Verifica se o valor informado pelo usuário é igual ao número sorteado ou igual aos limites. Se sim, o usuário perde **/
                if(chute == this@MainActivity.num || chute == this@MainActivity.min || chute == this@MainActivity.max) {
                    val result = "Você perdeu :("
                    val msg = "Dica: Se você informar o número sorteado, ou os valores limite, você perde! Número Sorteado: ${num}"
                    it.putExtra("RESULTADO", result)
                    it.putExtra("MENSAGEM", msg)
                    startActivity(it)
                } else if (chute < this@MainActivity.num) {
                    /** Define novo valor mínimo **/
                    this@MainActivity.min = chute
                    this@MainActivity.tvIntervalo.text = "Intervalo: ${min} - ${max}"
                } else if (chute > this@MainActivity.num) {
                    /** Define novo valor máximo **/
                    this@MainActivity.max = chute
                    this@MainActivity.tvIntervalo.text = "Intervalo: ${min} - ${max}"
                }

                this@MainActivity.etResposta.text.clear()
                /** Verifica se o usuário ganhou **/
                if(this@MainActivity.max - 1 == this@MainActivity.min + 1){
                    val result = "Você ganhou :)"
                    val msg = "Parabéns, você arrochou o número! (${this@MainActivity.num})"
                    it.putExtra("RESULTADO", result)
                    it.putExtra("MENSAGEM", msg)
                    startActivity(it)
                }
            }
        }
    }

    /** Restarta o jogo: retorna aos limites iniciais e gera outro número aleatório **/
    override fun onRestart() {
        super.onRestart()
        this.num = generateRandom()
        this.min = 1
        this.max = 100
        this.tvIntervalo.text = "Intervalo: ${min} - ${max}"
        Log.i("APP_ARROCHA", num.toString())
        this.etResposta.text.clear()
    }
}