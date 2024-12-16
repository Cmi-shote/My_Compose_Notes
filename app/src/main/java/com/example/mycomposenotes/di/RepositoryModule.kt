package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.data.NotesRepo
import com.example.mycomposenotes.notes.data.NotesRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NotesRepo> { NotesRepoImpl(get()) }
}