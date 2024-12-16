package com.example.mycomposenotes.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun NotesDao(): NotesDao
}