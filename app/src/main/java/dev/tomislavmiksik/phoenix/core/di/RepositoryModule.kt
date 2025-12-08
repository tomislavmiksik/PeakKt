package dev.tomislavmiksik.phoenix.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tomislavmiksik.phoenix.core.data.datastore.PhoenixPreferencesDataSource
import dev.tomislavmiksik.phoenix.core.data.remote.api.AuthApi
import dev.tomislavmiksik.phoenix.core.data.repository.AuthRepositoryImpl
import dev.tomislavmiksik.phoenix.core.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        dataSource: PhoenixPreferencesDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, dataSource)
    }
}