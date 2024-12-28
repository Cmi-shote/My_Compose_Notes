package com.example.mycomposenotes.notes.presentation.noteDetails

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposenotes.notes.domain.model.InvalidNoteException
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.useCase.NotesUseCases
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val notesUseCases: NotesUseCases,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _currentNote = mutableStateOf<Notes>(Notes())
    val currentNote: State<Notes> = _currentNote

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

    private val _snackBarMessage = mutableStateOf("")
    val snackBarMessage: State<String> = _snackBarMessage

    private val _selectedImageUris = mutableStateOf<List<Uri>>(emptyList())
    val selectedImageUris: State<List<Uri>> = _selectedImageUris

    private val _mediaId = mutableStateOf("")
    val mediaId: State<String> = _mediaId

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

                        val newNote = Notes(
                            id = currentNoteId.value,
                            title = noteTitle.value,
                            content = noteContent.value,
                            category = "",
                            backGroundImageId = noteBackground.value,
                            timeStamp = System.currentTimeMillis(),
                            mediaId = mediaId.value,
                            userId = firebaseAuth.currentUser?.uid ?: ""
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
                    event.note.id?.let { notesUseCases.deleteNoteFromFirebase(it) }
                    event.onDelete()
                }
            }
            is AddEditNoteEvent.UpdateImageUris -> {
                _selectedImageUris.value = event.imageUris
            }
            is AddEditNoteEvent.UpdateMediaId -> {
                _mediaId.value = event.mediaId
            }
            is AddEditNoteEvent.UploadMedia -> {
                viewModelScope.launch {
                    val uris = notesUseCases.uploadImagesToFirebaseUseCase(event.uris)
                    _mediaId.value = uris.joinToString(",")
                    event.onSuccess()
                }
            }
        }
    }

    fun getNote(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) { // Move this to background thread
            if (id != null) {
                val note = notesUseCases.getNoteUseCase(id) // Database operation
                _currentNote.value = note ?: Notes() // Update the state on the main thread
            }
        }
    }
}