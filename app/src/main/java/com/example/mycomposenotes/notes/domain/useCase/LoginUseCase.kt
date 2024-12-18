package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.presentation.login.AuthenticationState
import com.google.firebase.auth.FirebaseAuth

class LoginUseCase(private val firebaseAuth: FirebaseAuth) {
    fun execute(email: String, password: String, onResult: (AuthenticationState) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(AuthenticationState.Error("Email or password can't be empty"))
            return
        }
        onResult(AuthenticationState.Loading)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(AuthenticationState.Authenticated)
                } else {
                    onResult(AuthenticationState.Error(task.exception?.message ?: "Unknown error"))
                }
            }
    }
}