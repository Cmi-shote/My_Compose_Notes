package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class GetNotesFromFirebaseUseCase(
    private val repository: NotesRepo
) {
    suspend operator fun invoke() {
        val notes = repository.fetchNotesFromFirebase()

        //insert note into room db
        notes.forEach {
            repository.insertData(it)
        }
    }
}