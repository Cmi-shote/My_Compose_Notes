package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.mycomposenotes.notes.domain.model.Notes
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteContent(
    note: Notes,
    modifier: Modifier = Modifier,
    viewModel: AddEditViewModel = koinViewModel(),
    onBackPressed: () -> Unit = {},
) {
    val selectedImageUris by viewModel.selectedImageUris
    val noteBackground = if (note.backGroundImageId == -1) Notes.noteBackgroundImages[viewModel.noteBackground.value] else Notes.noteBackgroundImages[note.backGroundImageId]
    val title by viewModel.noteTitle
    val content by viewModel.noteContent
    val snackBarMessage by viewModel.snackBarMessage
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2),
        onResult = { viewModel.onEvent(AddEditNoteEvent.UpdateImageUris(it)) }
    )

    LaunchedEffect(note) {
        if (note.id != null) {
            viewModel.onEvent(AddEditNoteEvent.CurrentNoteId(note.id))
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(note.title))
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(note.content))
            viewModel.onEvent(AddEditNoteEvent.UpdateImageUris(viewModel.getUrisFromMediaId(note.mediaId)))
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { paddingValues ->
        Column(modifier = modifier.fillMaxSize()) {
            NoteContentTopBar(
                noteBackground = noteBackground ?: 0,
                onBackPressed = onBackPressed,
                onDeleteClicked = {
                    viewModel.onEvent(AddEditNoteEvent.DeleteNote(note, onDelete = { onBackPressed() }))
                },
                onClipClicked = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                onDoneBtnClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote(onSuccess = { onBackPressed() }))
                    scope.launch {
                        snackBarHostState.showSnackbar(message = snackBarMessage)
                    }
                }
            )

            NoteContentBody(
                title = title,
                onTitleChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                content = content,
                onContentChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                selectedImageUris = selectedImageUris,
                dateTime = note.timeStamp.toString()
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