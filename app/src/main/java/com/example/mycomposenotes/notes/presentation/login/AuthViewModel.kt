package com.example.mycomposenotes.notes.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mycomposenotes.notes.domain.useCase.AuthenticationUseCase

class AuthViewModel(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

    private val _email = mutableStateOf("")
    val email : State<String> = _email

    private val _password = mutableStateOf("")
    val password : State<String> = _password

    private val _authenticationState = mutableStateOf<AuthenticationState>(AuthenticationState.Unauthenticated)
    val authenticationState: State<AuthenticationState> = _authenticationState

    fun onEvent(event: AuthenticationEvent) {
        when(event) {
            is AuthenticationEvent.EnteredEmail -> {
                _email.value = event.email
            }
            is AuthenticationEvent.EnteredPassword -> {
                _password.value = event.password
            }
            is AuthenticationEvent.Login -> {
                login()
            }
            is AuthenticationEvent.Signup -> {
                signup()
            }
        }
    }

    private fun signup() {
        authenticationUseCase.signupUseCase.execute(email.value, password.value){
            _authenticationState.value = it
        }
    }

    private fun login() {
        authenticationUseCase.loginUseCase.execute(email.value, password.value){
            _authenticationState.value = it
        }
    }
}
