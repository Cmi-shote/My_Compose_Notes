package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo

/**
 * Use case for deleting notes from the repository.
 *
 * @property repository The repository providing access to notes data.
 */
class DeleteNotesUseCase(
    private val repository: NotesRepo
) {

    suspend operator fun invoke(note: Notes) {
        repository.deleteData(note)
    }
}