package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.presentation.login.AuthenticationState
import com.google.firebase.auth.FirebaseAuth

class SignOutUseCase(private val firebaseAuth: FirebaseAuth) {
    fun execute(onResult: (AuthenticationState) -> Unit) {
        firebaseAuth.signOut()
        onResult(AuthenticationState.Unauthenticated)
    }
}
