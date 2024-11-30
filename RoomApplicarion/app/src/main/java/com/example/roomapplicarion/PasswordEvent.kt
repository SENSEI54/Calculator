package com.example.roomapplicarion

sealed interface PasswordEvent {
    object SavePassword: PasswordEvent
    object ShowDialog: PasswordEvent
    object HideDialog: PasswordEvent
    data class setAccountName(val AccountName: String):PasswordEvent
    data class setEmail(val Email: String):PasswordEvent
    data class setPassword(val Password: String):PasswordEvent
    data class deletePassword(val password: Password):PasswordEvent
}