package com.example.roomapplicarion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapplicarion.EncryptionHelper.decrypt
import com.example.roomapplicarion.EncryptionHelper.encrypt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PasswordViewModel(private val dao: PasswordDAO) : ViewModel() {
    private val _state = MutableStateFlow(PasswordState())
    private val _passwords= dao.getPasswordByName().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val state= combine(_state,_passwords){state,password->
        state.copy(
            passwords= password
        ) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PasswordState())

    fun onEvent(event:PasswordEvent){
        when(event)
        {
            PasswordEvent.ShowDialog->{
                _state.update { it.copy(
                    isAdding = true
                ) }
            }
            PasswordEvent.HideDialog->{
                _state.update { it.copy(
                    isAdding = false
                ) }
            }

            PasswordEvent.SavePassword -> {
                val AcName=state.value.AccountName
                val mail=state.value.Email
                val Pass=state.value.Password

                if(AcName.isBlank()||mail.isBlank()||Pass.isBlank()) {
                    return
                }

                val passDetail = Password(
                    AccountName = AcName,
                    Email=mail,
                    Password = encrypt(Pass)
                )

                viewModelScope.launch {
                    dao.upsertPassword(passDetail)
                }
                _state.update {it.copy(
                        AccountName = "",
                        Email = "",
                        Password = ""
                    )
                }

            }
            is PasswordEvent.deletePassword -> {
                viewModelScope.launch {
                    dao.deletePassword(event.password)
                }
            }
            is PasswordEvent.setAccountName -> {
                _state.update { it.copy(
                    AccountName = event.AccountName
                ) }
            }
            is PasswordEvent.setEmail ->  _state.update { it.copy(
                Email =  event.Email
            ) }
            is PasswordEvent.setPassword ->  _state.update {
                it.copy(
                Password = event.Password
            ) }
        }
    }
}
