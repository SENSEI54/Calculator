package com.example.quizapplication

data class Question(
    val id: Int,
    val Question: String,
    val Image: Int,
    val Option1: String,
    val Option2: String,
    val Option3: String,
    val Option4: String,
    val correctAnswer: Int
)
