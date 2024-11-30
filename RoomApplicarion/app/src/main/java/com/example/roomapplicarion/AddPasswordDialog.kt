package com.example.roomapplicarion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roomapplicarion.PasswordEvent
import com.example.roomapplicarion.PasswordState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPasswordDialog(
    state: PasswordState,
    onEvent: (PasswordEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onEvent(PasswordEvent.HideDialog) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = state.AccountName,
                onValueChange = {
                    onEvent(PasswordEvent.setAccountName(it))
                },
                placeholder = {
                    Text(text = "Account Type")
                }
            )
            TextField(
                value = state.Email,
                onValueChange = {
                    onEvent(PasswordEvent.setEmail(it))
                },
                placeholder = {
                    Text(text = "Email")
                }
            )
            TextField(
                value = state.Password,
                onValueChange = {
                    onEvent(PasswordEvent.setPassword(it))
                },
                placeholder = {
                    Text(text = "Password")
                }
            )
            Button(
                onClick = { onEvent(PasswordEvent.SavePassword) },
                modifier = Modifier.background(Color.Black).align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Save")
            }
        }
    }
}
