package com.example.mycomposenotes.notes.domain.useCase

data class AuthenticationUseCase(
    val loginUseCase: LoginUseCase,
    val signupUseCase: SignupUseCase,
    val signOutUseCase: SignOutUseCase
)
