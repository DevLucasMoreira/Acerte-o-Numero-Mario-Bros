package com.example.mariobrosnumeros

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.mariobrosnumeros.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaNumeros: MutableList<Int> = mutableListOf()
    private var progresso = 0

    private val listaImags: MutableList<Int> = mutableListOf(
        R.drawable.n0, R.drawable.n1, R.drawable.n2, R.drawable.n3,
        R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7,
        R.drawable.n8, R.drawable.n9, R.drawable.n10, R.drawable.bloco
    )


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //Esconder StatusBar do APP

        binding.numeroSurpresa.setBackgroundResource(R.drawable.bloco) // Vai aparecer a caixa surpresa

        binding.buttonPlay.setOnClickListener { view ->
            val numeroDigitado: String = binding.editNumero.text.toString()

            if(numeroDigitado.isEmpty()){
                mensagem(view, "Coloque um número!", "#FF0000")
            }else{
                gerarNumeroAleatorio(view, numeroDigitado.toInt())
            }

        }

        binding.buttonReset.setOnClickListener {
            binding.editNumero.setText("")
            progresso = 0
            binding.linearProgressIndicator.setProgress(progresso, true)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun gerarNumeroAleatorio(view: View, numeroDigitado: Int){

        for (n in 0 .. 11){
            listaNumeros.add(n)
        }

        val numeroAleatorio = Random().nextInt(11)

        val imgNumero = when(numeroAleatorio){
            0 -> {
                listaImags[0]
            }
            1 -> {
                listaImags[1]
            }
            2 -> {
                listaImags[2]
            }
            3 -> {
                listaImags[3]
            }
            4 -> {
                listaImags[4]
            }
            5 -> {
                listaImags[5]
            }
            6 -> {
                listaImags[6]
            }
            7 -> {
                listaImags[7]
            }
            8 -> {
                listaImags[8]
            }
            9 -> {
                listaImags[9]
            }
            10 -> {
                listaImags[10]
            }
            else -> {
                listaImags[11]
            }
        }

        if (numeroDigitado != numeroAleatorio) {
            binding.numeroSurpresa.setBackgroundResource(R.drawable.bloco)
            mensagem(view, "Você errou! Tente novamente", "#FF0000")
            progresso += 30
            binding.linearProgressIndicator.setProgress(progresso, true)
        }else{
            mensagem(view, "Parabéns, você acertou!", "#2D9031")
            progresso -= 120
            binding.numeroSurpresa.setBackgroundResource(imgNumero)
            binding.editNumero.setText("")
            binding.linearProgressIndicator.setProgress(progresso, true)
            progresso = 0
        }

        if (progresso > 90){
            //navegar pra tela de Game Over
            val intent = Intent(this, GameOver::class.java)
            startActivity(intent)
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}