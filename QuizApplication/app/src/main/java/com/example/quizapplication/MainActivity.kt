package com.example.quizapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btn_Start)
        val etText: EditText= findViewById(R.id.et_Text)

        btnStart.setOnClickListener()
        {
            if(etText.text.isEmpty())
            {
               Toast.makeText(this,"Please Enter your Name",Toast.LENGTH_LONG).show()
            }
            else
            {
              val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Constants.username,etText.text.toString())
              startActivity(intent)
              finish()
            }
        }
   }
}