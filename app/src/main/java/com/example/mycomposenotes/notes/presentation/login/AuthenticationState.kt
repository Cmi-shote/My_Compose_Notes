package com.example.mycomposenotes.notes.presentation.login

sealed class AuthenticationState {
    object Authenticated : AuthenticationState()
    object Unauthenticated : AuthenticationState()
    object Loading : AuthenticationState()
    data class Error(val message: String) : AuthenticationState()
}