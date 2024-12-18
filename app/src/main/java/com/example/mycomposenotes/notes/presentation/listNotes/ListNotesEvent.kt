package com.example.mycomposenotes.notes.presentation.listNotes

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.welcome.SearchWidgetState

sealed class ListNotesEvent {
    data class DeleteNote(val note: Notes) : ListNotesEvent()
    data class SearchNotes(val query: String) : ListNotesEvent()
    data class SearchWidget(val widget: SearchWidgetState) : ListNotesEvent()
    object SignOut : ListNotesEvent()
}