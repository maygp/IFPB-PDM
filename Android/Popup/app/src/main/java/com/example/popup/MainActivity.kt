package com.example.popup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var btMsg : Button
    private lateinit var btInput : Button
    private lateinit var btData : Button
    private lateinit var btHora : Button
    private lateinit var btValores : Button
    private lateinit var btEscolha : Button
    private lateinit var btUnico : Button
    private lateinit var btVarios : Button
    private lateinit var view : View


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btMsg = findViewById(R.id.btMsg)
        this.btInput = findViewById(R.id.btInput)
        this.btData = findViewById(R.id.btData)
        this.btHora = findViewById(R.id.btHora)
        this.btValores = findViewById(R.id.btValores)
        this.btEscolha = findViewById(R.id.btEscolha)
        this.btUnico = findViewById(R.id.btUnico)
        this.btVarios = findViewById(R.id.btVarios)


        this.btMsg.setOnClickListener{mensagem()}
        this.btInput.setOnClickListener{input()}
        this.btData.setOnClickListener{data()}
        this.btHora.setOnClickListener{hora()}
        this.btValores.setOnClickListener{valores()}
        this.btEscolha.setOnClickListener{escolha()}
        this.btUnico.setOnClickListener{unico()}
        this.btVarios.setOnClickListener{varios()}
    }

    fun mensagem(){
        val janela = AlertDialog.Builder(this)

        janela.setTitle("Mensagem")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Que bom!")

        janela.setPositiveButton("OK") {
                _,_ -> Toast.makeText(this, "Ok", Toast.LENGTH_SHORT). show()
        }

        janela.setNegativeButton("Cancelar") {
                _,_ -> Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun input(){
        val janela = AlertDialog.Builder(this)
        this.view = EditText(this)

        janela.setTitle("Input")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Digite uma frase")
        janela.setView(this.view)

        janela.setPositiveButton("OK") { _,_ ->
            val msg = (this.view as EditText).text.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT). show()
        }

        janela.setNegativeButton("Cancelar") {
                _,_ -> Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun data(){
        val janela = AlertDialog.Builder(this)
        this.view = DatePicker(this)

        janela.setTitle("DatePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha uma Data")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") { _, _ ->
            val dp = this.view as DatePicker
            val msg = "${dp.dayOfMonth}/${dp.month + 1}/${dp.year}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun hora(){
        val janela = AlertDialog.Builder(this)
        this.view = TimePicker(this)

        janela.setTitle("TimePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha uma Hora")
        janela.setView(this.view)
        (this.view as TimePicker).setIs24HourView(true)

        janela.setPositiveButton("Ok") { _, _ ->
            val tp = this.view as TimePicker
            val msg = "${tp.currentHour}:${tp.currentMinute}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun valores(){
        val janela = AlertDialog.Builder(this)
        this.view = SeekBar(this)

        val sb = this.view as SeekBar

        sb.max = 100

        janela.setTitle("SeekBar")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha um Valor")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") { _, _ ->
            val msg = (this.view as SeekBar).progress.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()

    }

    fun escolha(){

        val janela = AlertDialog.Builder(this)
        this.view = Switch(this)

        janela.setTitle("Switch")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Sim ou Nao")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") { _, _ ->
            val msg = (this.view as Switch).isChecked.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()

    }

    fun unico(){
        val janela = AlertDialog.Builder(this)
        this.view = RadioGroup(this)
        val opcoes = arrayOf("Opcao 1", "Opcao 2", "Opcao 3")
        val rGroup = this.view as RadioGroup

        for (opcao in opcoes){
            val rButton = RadioButton(this)
            rButton.text = opcao
            rGroup.addView(rButton)
        }

        janela.setTitle("Radio Button")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha uma opcao")
        janela.setView(this.view)

        janela.setPositiveButton("OK") { _,_ ->
            val msg = rGroup.checkedRadioButtonId.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun varios(){
        val janela = AlertDialog.Builder(this)
        this.view = CheckBox(this)

        janela.setTitle("CheckBox")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha")
        janela.setView(this.view)

        janela.setPositiveButton("OK") { _,_ ->
            val msg = ""
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }
}