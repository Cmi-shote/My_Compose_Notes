package com.example.mycomposenotes.notes.presentation.noteDetails

import android.net.Uri
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposenotes.notes.domain.model.InvalidNoteException
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.useCase.NotesUseCases
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _currentNoteId = mutableStateOf<Int?>(null)
    val currentNoteId: State<Int?> = _currentNoteId

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent: State<String> = _noteContent

    private val _noteBackground = mutableIntStateOf(
        Notes.noteBackgroundImages.keys.random()
    )
    val noteBackground: State<Int> = _noteBackground

    private val _snackBarHostState = SnackbarHostState()

    private val _snackBarMessage = mutableStateOf("")
    val snackBarMessage: State<String> = _snackBarMessage

    private val _selectedImageUris = mutableStateOf<List<Uri>>(emptyList())
    val selectedImageUris: State<List<Uri>> = _selectedImageUris

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = event.title
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = event.content
            }

            is AddEditNoteEvent.CurrentNoteId -> {
                _currentNoteId.value = event.id
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        val mediaId = _selectedImageUris.value.joinToString(",") { it.toString() }

                        val newNote = Notes(
                            id = currentNoteId.value,
                            title = noteTitle.value,
                            content = noteContent.value,
                            category = "",
                            backGroundImageId = noteBackground.value,
                            timeStamp = System.currentTimeMillis(),
                            mediaId = mediaId
                        )
                        notesUseCases.addNotesUseCase(newNote)
                        notesUseCases.uploadNoteToFirebaseUseCase(newNote)

                        event.onSuccess()
                    } catch (e: InvalidNoteException) {
                        _snackBarMessage.value = e.message ?: "Couldn't save note"
                    }
                }
            }

            is AddEditNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNotesUseCase(event.note)
                    event.onDelete()
                }
            }
            is AddEditNoteEvent.UpdateImageUris -> {
                _selectedImageUris.value = event.imageUris
            }
        }
    }

    fun uploadNotes(notes: Notes) {
        viewModelScope.launch {
            notesUseCases.uploadNoteToFirebaseUseCase(notes)
        }

    }

    fun getUrisFromMediaId(mediaId: String): List<Uri> {
        return if (mediaId.isBlank()) {
            emptyList()
        } else {
            mediaId.split(",").map { Uri.parse(it) }
        }
    }
}