package com.mobile.medicapp.di

import com.mobile.medicapp.data.repository.AuthRepositoryImpl
import com.mobile.medicapp.data.repository.MedicineRepositoryImpl
import com.mobile.medicapp.domain.repository.AuthRepository
import com.mobile.medicapp.domain.repository.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMedicineRepository(
        medicineRepositoryImpl: MedicineRepositoryImpl
    ): MedicineRepository
}