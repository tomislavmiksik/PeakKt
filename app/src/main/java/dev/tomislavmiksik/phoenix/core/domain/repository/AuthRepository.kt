package dev.tomislavmiksik.phoenix.core.domain.repository

import dev.tomislavmiksik.phoenix.core.data.remote.dto.LoginResponseDto

interface AuthRepository {

    suspend fun login(email: String, password: String): LoginResponseDto

    suspend fun logout(): Result<Unit>

    suspend fun isAuthenticated(): Boolean
}
