package com.bignerdranch.android.glava2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton : Button
    private lateinit var next2Button : Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_Australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, true),
        Question(R.string.question_africa, true),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0

    private fun  checkAnswer (userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        next2Button = findViewById(R.id.next2_button)
        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener{
            checkAnswer(true)
        }
        falseButton.setOnClickListener{
            checkAnswer(false)
        }
        nextButton.setOnClickListener{
            if (currentIndex == 0){
                currentIndex = questionBank.size - 1
            }
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }
        next2Button.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

        }

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun updateQuestion(){
        val questionTextResId = questionBank [currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
}