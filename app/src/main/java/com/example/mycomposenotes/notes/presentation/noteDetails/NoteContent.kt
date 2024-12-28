package com.example.mycomposenotes.notes.presentation.noteDetails

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.utils.toFormattedDate
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteContent(
    noteId: Int?,
    modifier: Modifier = Modifier,
    viewModel: AddEditViewModel = koinViewModel(),
    onBackPressed: () -> Unit = {},
) {

    viewModel.getNote(noteId)

    val selectedImageUris by viewModel.selectedImageUris
    val note by viewModel.currentNote
    val noteBackground = if (note.backGroundImageId == -1) Notes.noteBackgroundImages[viewModel.noteBackground.value] else Notes.noteBackgroundImages[note.backGroundImageId]
    val dateTime = if (note.timeStamp == 0L) System.currentTimeMillis() else note.timeStamp
    val title by viewModel.noteTitle
    val content by viewModel.noteContent
    val snackBarMessage by viewModel.snackBarMessage
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2),
        onResult = { uris ->
            viewModel.onEvent(AddEditNoteEvent.UpdateImageUris(uris))
        }
    )

    LaunchedEffect(note) {
        if (note.id != null) {
            viewModel.onEvent(AddEditNoteEvent.CurrentNoteId(note.id!!))
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(note.title))
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(note.content))
            if (note.mediaId.isNotBlank()) {
                val uris = note.mediaId.split(",").map { Uri.parse(it) }
                viewModel.onEvent(AddEditNoteEvent.UpdateImageUris(uris))
            }
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
                    scope.launch {
                        try {
                            if (selectedImageUris.isNotEmpty()) {
                                viewModel.onEvent(AddEditNoteEvent.UploadMedia(selectedImageUris) {
                                    val mediaId = viewModel.mediaId.value
                                    viewModel.onEvent(AddEditNoteEvent.UpdateMediaId(mediaId))

                                    viewModel.onEvent(AddEditNoteEvent.SaveNote(onSuccess = { onBackPressed() }))
                                })
                            } else {
                                // No images selected, just save the note
                                viewModel.onEvent(AddEditNoteEvent.SaveNote(onSuccess = { onBackPressed() }))
                            }
                        } catch (e: Exception) {
                            snackBarHostState.showSnackbar(message = "Error saving note: ${e.message}")
                        }
                    }
                }
            )

            NoteContentBody(
                title = title,
                onTitleChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                content = content,
                onContentChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                selectedImageUris = selectedImageUris,
                dateTime = dateTime.toFormattedDate()
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