package com.example.mycomposenotes.notes.presentation.noteDetails

import com.example.mycomposenotes.notes.domain.model.Notes

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title: String) : AddEditNoteEvent()
    data class EnteredContent(val content: String) : AddEditNoteEvent()
    data class CurrentNoteId(val id: Int) : AddEditNoteEvent()
    data class ShowSnackBar(val message: String) : AddEditNoteEvent()
    data class SaveNote(val onSuccess: () -> Unit) : AddEditNoteEvent()
    data class DeleteNote(val note: Notes, val onDelete: () -> Unit) : AddEditNoteEvent()
}