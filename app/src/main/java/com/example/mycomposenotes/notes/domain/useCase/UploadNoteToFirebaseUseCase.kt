package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class UploadNoteToFirebaseUseCase(private val repository: NotesRepo) {
    /**
     * Uploads a note to Firebase
     */
    suspend operator fun invoke(note: Notes) {
        val noteToUpload = repository.getNoteByTimeStamp(note.timeStamp)

        repository.uploadNoteToFirebase(noteToUpload)
    }
}
