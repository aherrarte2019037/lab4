package com.group5.frontend.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "user_prefs"
)

class PreferenceManager(context: Context) {
    private val dataSource = context.dataStore

    object PreferencesKeys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
    }

    val authToken: Flow<String?> = dataSource.data
        .map { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataSource.edit { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN] = authToken
        }
    }

    suspend fun clearAuthToken() {
        dataSource.edit { preferences ->
            preferences.remove(PreferencesKeys.AUTH_TOKEN)
        }
    }
}
