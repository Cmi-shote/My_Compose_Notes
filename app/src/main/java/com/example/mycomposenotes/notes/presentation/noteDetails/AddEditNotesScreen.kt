package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddEditNotesScreen(
    noteId: Int?,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
//todo: change snack bar to toast
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

    NoteContent(
        modifier = modifier
            .padding(
                top = statusBarPadding.calculateTopPadding(),
                bottom = statusBarPadding.calculateBottomPadding()
            ),
        noteId = noteId,
        onBackPressed = onBackPressed,
    )

}