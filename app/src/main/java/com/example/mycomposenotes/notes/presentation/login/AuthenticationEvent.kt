package com.example.mycomposenotes.notes.presentation.login

sealed class AuthenticationEvent {
    data class EnteredEmail(val email: String) : AuthenticationEvent()
    data class EnteredPassword(val password: String) : AuthenticationEvent()
    object Login : AuthenticationEvent()
    object Signup : AuthenticationEvent()
}
