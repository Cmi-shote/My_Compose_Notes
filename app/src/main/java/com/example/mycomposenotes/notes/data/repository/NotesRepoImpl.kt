package com.example.mycomposenotes.notes.data.repository

import com.example.mycomposenotes.notes.data.dataSource.NotesDao
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import kotlinx.coroutines.flow.Flow

class NotesRepoImpl(
    private val notesDao: NotesDao
) : NotesRepo {
    override fun getNotes(): Flow<List<Notes>> {
        return notesDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Notes? {
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