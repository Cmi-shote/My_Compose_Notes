package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class GetNoteUseCase(
    private val repository: NotesRepo
) {

    /**
     * Retrieves a note by its ID from the repository.
     *
     * @param id The ID of the note to retrieve.
     * @return The note with the specified ID, or null if not found.
     */
    suspend operator fun invoke(id: Int): Notes? {
        return repository.getNoteById(id)
    }
}