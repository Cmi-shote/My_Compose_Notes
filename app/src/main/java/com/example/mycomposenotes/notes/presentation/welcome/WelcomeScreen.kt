package com.example.mycomposenotes.notes.presentation.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            WelcomeScreenTopBar()
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        content = { paddingValues ->
            WelcomeScreenContent(modifier = Modifier
                .padding(
                    start = paddingValues.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateRightPadding(LayoutDirection.Ltr) + 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                onClick = onClick
            )
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun WelcomeScreenPreview() {
    MyComposeNotesTheme {
        WelcomeScreen()
    }
}