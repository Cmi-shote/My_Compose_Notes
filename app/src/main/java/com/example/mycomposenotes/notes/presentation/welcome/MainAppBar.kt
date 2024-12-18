package com.example.mycomposenotes.notes.presentation.welcome

import androidx.compose.runtime.Composable
import com.example.mycomposenotes.notes.domain.model.Notes

@Composable
fun MainAppBar(
    notes: List<Notes>,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            WelcomeScreenTopBar(
                onClick = onSearchTriggered,
                notes = notes
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}
