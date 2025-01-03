package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.presentation.login.AuthenticationState
import com.google.firebase.auth.FirebaseAuth
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SignupUseCaseTest {

    private val firebaseAuth: FirebaseAuth = mockk()
    private val signupUseCase = SignupUseCase(firebaseAuth)

    @Test
    fun execute_withEmptyEmailOrPassword_returnsError() {
        val onResult: (AuthenticationState) -> Unit = mockk(relaxed = true)

        // Test with empty email
        signupUseCase.execute("", "password", onResult)
        verify { onResult(AuthenticationState.Error("Email or password can't be empty")) }

        // Test with empty password
        signupUseCase.execute("test@example.com", "", onResult)
        verify { onResult(AuthenticationState.Error("Email or password can't be empty")) }
    }
}