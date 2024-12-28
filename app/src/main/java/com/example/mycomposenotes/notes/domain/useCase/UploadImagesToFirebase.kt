package com.example.mycomposenotes.notes.domain.useCase

import android.net.Uri
import com.example.mycomposenotes.notes.domain.repository.NotesRepo

class UploadImagesToFirebaseUseCase(private val repository: NotesRepo) {
    suspend operator fun invoke(uris: List<Uri>): List<Uri> {
        return repository.uploadImagesToFirebase(uris)
    }
}
