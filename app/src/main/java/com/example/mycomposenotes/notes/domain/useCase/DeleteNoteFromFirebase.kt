package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class DeleteNoteFromFirebase(
    private val repository: NotesRepo
) {
    suspend operator fun invoke(noteId: Int) {
        repository.deleteNoteFromFirebase(noteId)
    }
}
