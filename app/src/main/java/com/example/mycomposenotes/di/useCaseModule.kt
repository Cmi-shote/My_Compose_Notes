package com.example.mycomposenotes.di

import com.example.mycomposenotes.notes.domain.useCase.AddNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.AuthenticationUseCase
import com.example.mycomposenotes.notes.domain.useCase.DeleteNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.GetNoteUseCase
import com.example.mycomposenotes.notes.domain.useCase.GetNotesFromFirebaseUseCase
import com.example.mycomposenotes.notes.domain.useCase.GetNotesUseCase
import com.example.mycomposenotes.notes.domain.useCase.LoginUseCase
import com.example.mycomposenotes.notes.domain.useCase.NotesUseCases
import com.example.mycomposenotes.notes.domain.useCase.SignOutUseCase
import com.example.mycomposenotes.notes.domain.useCase.SignupUseCase
import com.example.mycomposenotes.notes.domain.useCase.UploadNoteToFirebaseUseCase
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddNotesUseCase(get()) }
    factory { DeleteNotesUseCase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { GetNoteUseCase(get()) }
    factory { NotesUseCases(get(), get(), get(), get(), get(), get()) }
    factory { LoginUseCase(get()) }
    factory { SignOutUseCase(get()) }
    factory { SignupUseCase(get()) }
    factory { AuthenticationUseCase(get(), get(), get()) }
    factory { GetNotesFromFirebaseUseCase(get()) }
    factory { UploadNoteToFirebaseUseCase(get()) }
}