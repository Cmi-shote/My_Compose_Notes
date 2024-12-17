package com.example.mycomposenotes.notes.presentation.listNotes

import com.example.mycomposenotes.notes.domain.model.Notes

sealed class ListNotesEvent {
    data class DeleteNote(val note: Notes) : ListNotesEvent()
    data class SearchNotes(val query: String) : ListNotesEvent()
}