package com.group5.frontend.ui.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group5.frontend.io.ApiService
import com.group5.frontend.io.RegisterRequest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    navController: NavController,
    apiService: ApiService
) {
    val scope = rememberCoroutineScope()
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (firstname, setFirstname) = remember { mutableStateOf("") }
    val (lastname, setLastname) = remember { mutableStateOf("") }

    fun onRegisterClick(context: Context) {
        if (email.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()) {
            Toast.makeText(context, "Error, completa tus datos", Toast.LENGTH_SHORT).show()
        }
        val request = RegisterRequest(email, password, firstname, lastname)
        scope.launch {
            val response = apiService.register(request)

            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    if (apiResponse.success) {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        navController.navigate("login")
                    } else {
                        Toast.makeText(context, apiResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Error, intenta otra vez", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        Text(text = "Crear cuenta", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { setEmail(it) },
            label = { Text("Correo") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { setPassword(it) },
            label = { Text("Contraseña") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = firstname,
            onValueChange = { setFirstname(it) },
            label = { Text("Nombre") },
        )
        OutlinedTextField(
            value = lastname,
            onValueChange = { setLastname(it) },
            label = { Text("Apellido") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onRegisterClick(context) }) {
            Text(text = "Registrarse")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Ya tienes una cuenta? Inicia sesión")
        }
    }
}