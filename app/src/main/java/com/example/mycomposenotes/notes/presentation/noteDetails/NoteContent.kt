package com.example.mycomposenotes.notes.presentation.noteDetails

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    LaunchedEffect(noteId) {
        viewModel.getNote(noteId)
    }

    val selectedImageUris by viewModel.selectedImageUris
    val note by viewModel.currentNote
    val title by viewModel.noteTitle
    val content by viewModel.noteContent
    val scope = rememberCoroutineScope()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    val noteBackground = remember(note.id, note.backGroundImageId) {
        when {
            note.id == null -> Notes.noteBackgroundImages[viewModel.noteBackground.value]
            note.backGroundImageId == -1 -> Notes.noteBackgroundImages[viewModel.noteBackground.value]
            else -> Notes.noteBackgroundImages[note.backGroundImageId]
        }
    }

    val dateTime = if (note.timeStamp == 0L) System.currentTimeMillis() else note.timeStamp

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2),
        onResult = { uris ->
            viewModel.onEvent(AddEditNoteEvent.UpdateImageUris(uris))
        }
    )

    LaunchedEffect(note.id) {
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

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                noteBackground?.let { background ->
                    NoteContentTopBar(
                        noteBackground = background,
                        onBackPressed = onBackPressed,
                        onDeleteClicked = {
                            viewModel.onEvent(
                                AddEditNoteEvent.DeleteNote(note, onDelete = { onBackPressed() })
                            )
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
                                            viewModel.onEvent(
                                                AddEditNoteEvent.SaveNote(onSuccess = { onBackPressed() })
                                            )
                                        })
                                    } else {
                                        viewModel.onEvent(
                                            AddEditNoteEvent.SaveNote(onSuccess = { onBackPressed() })
                                        )
                                    }
                                } catch (e: Exception) {
                                    Log.e("NoteContent", "Error saving note", e)
                                }
                            }
                        },
                        modifier = modifier.statusBarsPadding()
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {

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

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
                    .clickable(enabled = false) { },
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
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