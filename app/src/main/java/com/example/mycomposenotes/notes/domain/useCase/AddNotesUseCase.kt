package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.InvalidNoteException
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class AddNotesUseCase(private val repository: NotesRepo) {

    /**
     * Adds a new note to the repository after validation.
     *
     * @param note The note to be added.
     * @throws InvalidNoteException If the note title or content is blank.
     */
    suspend operator fun invoke(note: Notes) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Note title can not be blank")
        }

        if (note.content.isBlank()) {
            throw InvalidNoteException("Note content can not be blank")
        }

        repository.insertData(note)
    }
}