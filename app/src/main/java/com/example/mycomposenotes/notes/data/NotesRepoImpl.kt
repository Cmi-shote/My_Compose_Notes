package com.example.mycomposenotes.notes.data

import kotlinx.coroutines.flow.Flow

class NotesRepoImpl(
    private val notesDao: NotesDao
) : NotesRepo {
    override fun getNotes(): Flow<List<Notes>> {
        return notesDao.getNotes()
    }

    override suspend fun getNoteById(id: String): Notes? {
        return notesDao.getNoteById(id)
    }

    override suspend fun insertData(notes: Notes) {
        return notesDao.insertData(notes)
    }

    override suspend fun deleteData(notes: Notes) {
        return notesDao.deleteData(notes)
    }

    override suspend fun updateData(notes: Notes) {
        return notesDao.updateData(notes)
    }
}