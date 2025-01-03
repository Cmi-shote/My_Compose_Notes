package com.example.mycomposenotes.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.data.dataSource.NotesDao

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}