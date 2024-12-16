package com.example.mycomposenotes.notes.data

import kotlinx.coroutines.flow.Flow

interface NotesRepo {

    fun getNotes(): Flow<List<Notes>>

    suspend fun getNoteById(id: String): Notes?

    suspend fun insertData(notes: Notes)

    suspend fun deleteData(notes: Notes)

    suspend fun updateData(notes: Notes)
}