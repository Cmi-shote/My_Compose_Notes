package com.example.mycomposenotes.notes.presentation.noteDetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.domain.model.Notes
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteContent(
    note: Notes,
    modifier: Modifier = Modifier,
    viewModel: AddEditViewModel = koinViewModel<AddEditViewModel>(),
    onBackPressed: () -> Unit = {},
    onCameraClicked: () -> Unit = {}, //todo control with viewmodel
    onClipClicked: () -> Unit = {}, //todo control with viewmodel
    onDeleteClicked: () -> Unit = {} //todo control with viewmodel
) {

    LaunchedEffect(note) {
        if (note.id != null) {
            viewModel.onEvent(AddEditNoteEvent.CurrentNoteId(note.id))
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(note.title))
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(note.content))
        }
    }

    val noteBackground = if (note.backGroundImageId == 0) viewModel.noteBackground.value else note.backGroundImageId
    val title by viewModel.noteTitle
    val content by viewModel.noteContent

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            noteBackground.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.matchParentSize(),
                    alpha = 0.2f
                )
            }

            NoteDetailsTopBar(
                onBackPressed = onBackPressed,
                onCameraClicked = onCameraClicked,
                onDeleteClicked = onDeleteClicked,
                onClipClicked = onClipClicked,
                onDoneBtnClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "Clock Icon",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "21/02", // This can be dynamic based on your data model
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = title,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                label = { Text("Title") },
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(R.color.white),
                    focusedContainerColor = colorResource(R.color.white),
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = content,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                label = { Text("Content") },
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(R.color.white),
                    focusedContainerColor = colorResource(R.color.white),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}



//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun NoteDetailsScreenContentPreview() {
//    MyComposeNotesTheme {
//        val noteSample = Notes(
//            id = 12334324,
//            title = "A Right Media Mix Can Make The Difference",
//            content = stringResource(R.string.lorem_ipsum),
//            timeStamp = System.currentTimeMillis(),
//            category = "Work",
//            mediaId = "1",
//            backGroundImageId = R.drawable.note_background_6
//        )
//        NoteContent(note = null)
//    }
//}