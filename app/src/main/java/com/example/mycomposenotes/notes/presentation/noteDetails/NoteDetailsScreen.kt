package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.mycomposenotes.notes.model.Notes

@Composable
fun NoteDetailsScreen(note: Notes?, modifier: Modifier = Modifier) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

    NoteContent(
        modifier = modifier
            .padding(
                start = statusBarPadding.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp,
                top = statusBarPadding.calculateTopPadding(),
                end = statusBarPadding.calculateRightPadding(LayoutDirection.Ltr) + 16.dp,
                bottom = statusBarPadding.calculateBottomPadding()
            ),
        note = note
    )

}