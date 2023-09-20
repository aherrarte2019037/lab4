package com.group5.frontend.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group5.frontend.utils.PreferenceManager
import kotlinx.coroutines.launch

@Composable
fun HomePage(navController: NavController, preferenceManager: PreferenceManager) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome Home!", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {}) {
            Text("Explore")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                preferenceManager.clearAuthToken()
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }
        }) {
            Text(text = "Logout")
        }
    }
}