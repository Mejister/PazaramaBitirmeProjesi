package com.example.pazarama_bitirme_app.domain.di

import com.example.pazarama_bitirme_app.domain.usecase.login.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

class UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(firebaseAuth: FirebaseAuth) = LoginUseCase(firebaseAuth)
}
