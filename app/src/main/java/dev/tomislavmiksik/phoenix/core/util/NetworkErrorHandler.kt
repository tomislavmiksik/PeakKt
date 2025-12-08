package dev.tomislavmiksik.phoenix.core.util

import dev.tomislavmiksik.phoenix.core.data.remote.dto.ErrorResponseDto
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

object NetworkErrorHandler {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                try {
                    val errorBody = throwable.response()?.errorBody()?.string()
                    if (errorBody != null) {
                        // Try parsing as JSON first
                        try {
                            val errorResponse = json.decodeFromString<ErrorResponseDto>(errorBody)
                            errorResponse.errorMessage
                        } catch (e: Exception) {
                            // If not JSON, return plain text response
                            errorBody
                        }
                    } else {
                        "Server error: ${throwable.code()}"
                    }
                } catch (e: Exception) {
                    "Server error: ${throwable.code()}"
                }
            }
            is IOException -> "Network connection failed. Please check your internet connection."
            else -> throwable.message ?: "Unknown error occurred"
        }
    }
}
