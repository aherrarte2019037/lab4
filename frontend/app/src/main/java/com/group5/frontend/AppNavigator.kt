package com.group5.frontend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group5.frontend.io.ApiService
import com.group5.frontend.ui.pages.HomePage
import com.group5.frontend.ui.pages.LoadingPage
import com.group5.frontend.ui.pages.LoginPage
import com.group5.frontend.ui.pages.RegisterPage
import com.group5.frontend.utils.PreferenceManager

@Composable
fun AppNavigator(preferenceManager: PreferenceManager, apiService: ApiService) {
    val navController = rememberNavController()
    val authToken by preferenceManager.authToken.collectAsState(initial = "loading")

    LaunchedEffect(authToken) {
        when {
            authToken == "loading" -> navController.navigate("loading") {
                popUpTo(0) { inclusive = true }
            }
            authToken != null -> navController.navigate("home") {
                popUpTo(0) { inclusive = true }
            }
            else -> navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginPage(navController, preferenceManager, apiService ) }
        composable("register") { RegisterPage(navController, preferenceManager) }
        composable("home") { HomePage(navController, preferenceManager) }
        composable("loading") { LoadingPage() }
    }
}