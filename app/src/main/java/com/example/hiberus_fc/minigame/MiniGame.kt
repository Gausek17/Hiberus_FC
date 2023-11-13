package com.example.hiberus_fc.minigame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hiberus_fc.R
import com.example.hiberus_fc.databinding.ActivityMiniGameBinding
import com.example.hiberus_fc.main.MainActivity
import com.example.hiberus_fc.utilities.MyMediaPlayer

class MiniGame : AppCompatActivity() {

    private lateinit var binding: ActivityMiniGameBinding

    private val questions = arrayOf("¿Quién es el actual campeón del mundo?",
        "¿Cuántos balones de oro tiene Cristiano Ronaldo?",
        "¿En qué año España fue campeona del mundo?",
        "¿Cuál es el país con más copas del mundo?",
        "¿Quién es el jugador que más veces vio la tarjeta roja en la historia de la Liga?",
        "¿Cuál de estos equipos de fútbol no es de Londres?",
        "¿En qué año fue el célebre Maracanazo?",
        "El primer partido de fútbol que se jugó entre selecciones enfrentó a...",
        "¿Qué entrenador acumula más participaciones en los Mundiales?",
        "¿Quién fue el entrenador que más temporadas estuvo al frente del FC Barcelona?")

    private val options = arrayOf(arrayOf("Argentina", "Francia", "Alemania"),
        arrayOf("4","5","6"),
        arrayOf("2006","2012","2010"),
        arrayOf("Brasil","Alemania", "Argentina"),
        arrayOf("Pepe", "Sergio Ramos", "Pablo Alfaro"),
        arrayOf("Cheslea", "Arsenal", "Wolves"),
        arrayOf("1950","1962","1978"),
        arrayOf("Irlanda y Gales","Escocia e Inglaterra","Inglaterra y Alemania"),
        arrayOf("Mario Zagallo", "Joaquim Löw", "Carlos Alberto Parreira"),
        arrayOf("Jack Grennwell","Pep Guardiola", "Luis Van Gaal")
    )

    private val correctAnswers = arrayOf(0,1,2,0,1,2,0,1,2,0)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiniGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile()
        MyMediaPlayer.getMediaPlayerInstance().playAudioFile(this, R.raw.quizsound)
        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

        binding.buttonNext.setOnClickListener {
            restartQuiz()
        }

        binding.buttonHome.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
    }


    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(0,21,46))
        binding.option2Button.setBackgroundColor(Color.rgb(0,21,46))
        binding.option3Button.setBackgroundColor(Color.rgb(0,21,46))
    }


    private fun showResults(){
        Toast.makeText(this, "Tu puntuación: $score de ${questions.size}", Toast.LENGTH_LONG).show()
        binding.buttonNext.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors((selectedAnswerIndex))
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size -1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        }else{
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.buttonNext.isEnabled = false
    }
}