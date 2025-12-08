package dev.tomislavmiksik.phoenix.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhoenixPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")
    }


    suspend fun saveJwtToken(jwtToken: String) {
        dataStore.edit { preferences ->
            preferences[JWT_TOKEN_KEY] = jwtToken
        }
    }

    suspend fun getJwtToken(): String? {
        return getJwtTokenFlow().firstOrNull()
    }

    fun getJwtTokenFlow(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[JWT_TOKEN_KEY]
        }
    }

    suspend fun clearJwtToken() {
        dataStore.edit { preferences ->
            preferences.remove(JWT_TOKEN_KEY)
        }
    }
}