package com.example.mycomposenotes.notes.presentation.common

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposenotes.notes.presentation.login.AuthenticationEvent
import com.example.mycomposenotes.notes.presentation.login.AuthViewModel
import com.example.mycomposenotes.notes.presentation.login.AuthenticationState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    titleText: String = "",
    buttonText: String = "",
    onLoginClick: () -> Unit = {},
    onClick: () -> Unit = {},
    onSuccess: () -> Unit = {},
    viewModel: AuthViewModel = koinViewModel<AuthViewModel>(),
) {

    val email = viewModel.email
    val password = viewModel.password
    val authenticationState by viewModel.authenticationState
    val context = LocalContext.current

    LaunchedEffect(authenticationState) {
        when (authenticationState) {
            is AuthenticationState.Authenticated -> {
                onSuccess()
            }
            is AuthenticationState.Error -> {
                val errorMessage = (authenticationState as AuthenticationState.Error).message

                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
//            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = titleText, fontSize = 32.sp)

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        viewModel.onEvent(AuthenticationEvent.EnteredEmail(it))
                    },
                    label = { Text("Email") },
                    placeholder = { Text("Enter your email") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        viewModel.onEvent(AuthenticationEvent.EnteredPassword(it))
                    },
                    label = { Text("Password") },
                    placeholder = { Text("Enter your password") }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onLoginClick,
                    shape = RoundedCornerShape(8.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.Black
//                    ),
                    enabled = authenticationState !is AuthenticationState.Loading
                ) {
                    Text(
                        text = titleText,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = { onClick() }) {
                    Text(
                        text = buttonText,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}