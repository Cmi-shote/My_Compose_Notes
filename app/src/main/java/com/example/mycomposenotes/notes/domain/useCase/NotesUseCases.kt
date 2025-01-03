package com.example.mycomposenotes.notes.domain.useCase

data class NotesUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNotesUseCase: DeleteNotesUseCase,
    val getNoteUseCase: GetNoteUseCase,
    val addNotesUseCase: AddNotesUseCase,
    val getNotesFromFirebaseUseCase: GetNotesFromFirebaseUseCase,
    val uploadNoteToFirebaseUseCase: UploadNoteToFirebaseUseCase,
    val deleteNoteFromFirebase: DeleteNoteFromFirebase,
    val uploadImagesToFirebaseUseCase: UploadImagesToFirebaseUseCase
)