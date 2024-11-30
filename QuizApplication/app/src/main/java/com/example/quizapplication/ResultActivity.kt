package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizapplication.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var mCorrectAnswers=intent.getIntExtra(Constants.correctAnswer,0)
        var name=findViewById<TextView>(R.id.tv_Username)
        var tvScore=findViewById<TextView>(R.id.tvCorrect)
        var button=findViewById<Button>(R.id.finishButton)


        name.setText(intent.getStringExtra(Constants.username))
        tvScore.setText("You have scored "+"${mCorrectAnswers} "+"out of"+"10")

        button.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}