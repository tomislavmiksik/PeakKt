package dev.tomislavmiksik.peak.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tomislavmiksik.peak.core.data.healthconnect.HealthConnectRepositoryImpl
import dev.tomislavmiksik.peak.core.domain.repository.HealthConnectRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HealthConnectModule {

    @Binds
    @Singleton
    abstract fun bindHealthConnectRepository(
        impl: HealthConnectRepositoryImpl
    ): HealthConnectRepository
}
