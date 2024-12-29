package com.example.mycomposenotes.notes.presentation.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.presentation.common.LoginContent
import com.example.mycomposenotes.notes.presentation.login.AuthViewModel
import com.example.mycomposenotes.notes.presentation.login.AuthenticationEvent
import com.example.mycomposenotes.notes.presentation.welcome.WelcomeScreenTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onSuccess: () -> Unit = {},
    viewModel: AuthViewModel = koinViewModel<AuthViewModel>()
) {
    Scaffold(
        topBar = {
            WelcomeScreenTopBar()
        },
        modifier = modifier
            .fillMaxSize(),
        content = { paddingValues ->
            LoginContent(
                modifier = Modifier.padding(paddingValues),
                titleText = stringResource(R.string.create_account),
                buttonText = stringResource(R.string.already_have_an_account),
                onLoginClick = { viewModel.onEvent(AuthenticationEvent.Signup) },
                onClick = onClick,
                onSuccess = onSuccess
            )
        }
    )
}