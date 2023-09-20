package com.group5.frontend.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group5.frontend.utils.PreferenceManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController, preferenceManager: PreferenceManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Register Screen", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("login") }) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account? Login")
        }
    }
}