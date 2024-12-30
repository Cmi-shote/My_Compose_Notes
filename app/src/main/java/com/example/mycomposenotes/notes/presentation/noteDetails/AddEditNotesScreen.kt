package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddEditNotesScreen(
    noteId: Int?,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
//todo: change snack bar to toast

    NoteContent(
        modifier = modifier,
        noteId = noteId,
        onBackPressed = onBackPressed,
    )

}