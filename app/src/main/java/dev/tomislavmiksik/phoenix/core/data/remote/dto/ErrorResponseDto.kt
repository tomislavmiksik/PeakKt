package dev.tomislavmiksik.phoenix.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val statusCode: String,
    val errorMessage: String
)
