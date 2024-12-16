package com.example.mycomposenotes.di

import android.app.Application
import androidx.room.Room
import com.example.mycomposenotes.notes.data.NotesDao
import com.example.mycomposenotes.notes.data.NotesDatabase
import com.example.mycomposenotes.utils.Constants.NOTE_DATABASE_NAME

fun provideDataBase(application: Application) : NotesDatabase =
    Room.databaseBuilder(
        application,
        NotesDatabase::class.java,
        NOTE_DATABASE_NAME
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(notesDataBase: NotesDatabase): NotesDao = notesDataBase.NotesDao()
