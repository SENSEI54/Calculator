import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomapplicarion.Password
import com.example.roomapplicarion.PasswordEvent
import com.example.roomapplicarion.PasswordState
import com.example.roomapplicarion.AddPasswordDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: PasswordState, onEvent: (PasswordEvent) -> Unit, navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(PasswordEvent.ShowDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Account")
            }
        }
    ) { padding ->
        if (state.isAdding) {
            AddPasswordDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(contentPadding = padding) {
            items(state.passwords) { password ->
                PasswordItem(password = password, navController = navController)
            }
        }
    }
}

@Composable
fun PasswordItem(password: Password, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("account_details/${password.id}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = password.AccountName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "********",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Go to details"
            )
        }
    }
}