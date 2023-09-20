package com.group5.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.group5.frontend.io.ApiService
import com.group5.frontend.ui.theme.FrontendTheme
import com.group5.frontend.utils.PreferenceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferenceManager = PreferenceManager(applicationContext)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.8/projects/lab4/backend/src/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(preferenceManager, apiService)
                }
            }
        }
    }
}
