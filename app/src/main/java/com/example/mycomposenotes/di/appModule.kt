package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.data.NotesRepo
import com.example.mycomposenotes.notes.data.NotesRepoImpl
import com.example.mycomposenotes.notes.presentation.listNotes.ListNotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NotesRepo> { NotesRepoImpl(get()) }
    viewModel { ListNotesViewModel(get()) }
}