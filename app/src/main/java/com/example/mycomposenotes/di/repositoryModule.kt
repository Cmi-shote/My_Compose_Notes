package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import com.example.mycomposenotes.notes.data.repository.NotesRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NotesRepo> { NotesRepoImpl(get(), get(), get(), get()) }
}