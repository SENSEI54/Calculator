package com.example.roomapplicarion

data class PasswordState (
    val passwords : List<Password> = emptyList(),
    val AccountName:String= "",
    val Email:String = "",
    val Password:String = "",
    val isAdding:Boolean =false,

)