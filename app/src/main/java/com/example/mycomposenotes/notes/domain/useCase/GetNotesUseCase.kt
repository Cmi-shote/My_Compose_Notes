package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case for fetching notes from the repository and optionally filtering them based on a query.
 *
 * @property repository The repository providing access to notes data.
 */
class GetNotesUseCase(
    private val repository: NotesRepo
) {
    operator fun invoke(query: String): Flow<List<Notes>> {
        return repository.getNotes().map { notes ->
            if (query.isBlank()) {
                notes
            } else {
                notes.filter {
                    it.title.contains(query, ignoreCase = true)
                }
            }
        }
    }

}