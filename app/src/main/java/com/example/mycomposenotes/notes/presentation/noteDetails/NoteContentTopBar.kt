package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NoteContentTopBar(
    noteBackground: Int,
    onBackPressed: () -> Unit,
    onDeleteClicked: () -> Unit,
    onClipClicked: () -> Unit,
    onDoneBtnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painter = painterResource(id = noteBackground),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.matchParentSize(),
            alpha = 0.2f
        )

        NoteDetailsTopBar(
            onBackPressed = onBackPressed,
            onDeleteClicked = onDeleteClicked,
            onClipClicked = onClipClicked,
            onDoneBtnClick = onDoneBtnClick
        )
    }
}
