package com.example.mycomposenotes.notes.presentation.noteDetails

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title: String) : AddEditNoteEvent()
    data class EnteredContent(val content: String) : AddEditNoteEvent()
    data class CurrentNoteId(val id: Int) : AddEditNoteEvent()
    data class ShowSnackBar(val message: String) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
    object NavigateBack : AddEditNoteEvent()
    object DeleteNote : AddEditNoteEvent()
}