package com.example.mycomposenotes.notes.data.database

import android.app.Application
import androidx.room.Room
import com.example.mycomposenotes.notes.data.dataSource.NotesDao
import com.example.mycomposenotes.notes.presentation.utils.Constants.NOTE_DATABASE_NAME

fun provideDataBase(application: Application) : NotesDatabase =
    Room.databaseBuilder(
        application,
        NotesDatabase::class.java,
        NOTE_DATABASE_NAME
    ).build()

fun provideDao(notesDataBase: NotesDatabase): NotesDao = notesDataBase.NotesDao()
