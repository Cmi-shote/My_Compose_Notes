package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.domain.useCase.AddNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.DeleteNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.GetNoteUseCase
import com.example.mycomposenotes.notes.domain.useCase.GetNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.NotesUseCases
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddNotesUseCase(get()) }
    factory { DeleteNotesUseCase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { GetNoteUseCase(get()) }
    factory { NotesUseCases(get(), get(), get(), get()) }
}