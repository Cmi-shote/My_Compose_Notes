package com.example.mycomposenotes.notes.domain.repository

import com.example.mycomposenotes.notes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepo {

    fun getNotes(): Flow<List<Notes>>

    suspend fun getNoteById(id: Int): Notes?

    suspend fun insertData(notes: Notes)

    suspend fun deleteData(notes: Notes)

    suspend fun updateData(notes: Notes)

    suspend fun fetchNotesFromFirebase(): List<Notes>

    suspend fun uploadNoteToFirebase(notes: Notes)

    suspend fun getNoteByTimeStamp(timeStamp: Long): Notes

    suspend fun deleteNoteFromFirebase(noteId: Int)
}