package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.presentation.listNotes.ListNotesViewModel
import com.example.mycomposenotes.notes.presentation.noteDetails.AddEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListNotesViewModel(get()) }
    viewModel { AddEditViewModel(get()) }
}