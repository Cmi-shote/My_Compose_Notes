package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlin.test.assertEquals

class GetNotesUseCaseTest {

    private val repository = mockk<NotesRepo>()
    private val getNotesUseCase = GetNotesUseCase(repository)

    @Test
    fun getNotes_withBlankQuery_returnsAllNotes() = runTest {
        val notesList = listOf(
            Notes(id = 1, title = "Note 1", content = "Content 1", timeStamp = 123L),
            Notes(id = 2, title = "Note 2", content = "Content 2", timeStamp = 124L)
        )

        coEvery { repository.getNotes() } returns flowOf(notesList)

        val result = getNotesUseCase("")

        result.first().let { notes ->
            assertEquals(2, notes.size)
            assertEquals("Note 1", notes[0].title)
            assertEquals("Note 2", notes[1].title)
        }
    }

    @Test
    fun getNotes_withQuery_filtersNotesByTitle() = runTest {
        val notesList = listOf(
            Notes(id = 1, title = "first note", content = "Content 1", timeStamp = 123L),
            Notes(id = 2, title = "second note", content = "Content 2", timeStamp = 124L),
            Notes(id = 3, title = "the first new note", content = "Content 3", timeStamp = 125L)
        )

        coEvery { repository.getNotes() } returns flowOf(notesList)

        val result = getNotesUseCase("first")

        result.first().let { notes ->
            assertEquals(2, notes.size)
            assertEquals("first note", notes[0].title)
            assertEquals("the first new note", notes[1].title)
        }
    }

    @Test
    fun getNotes_withQuery_returnsEmptyListWhenNoMatch() = runTest {
        val notesList = listOf(
            Notes(id = 1, title = "Note 1", content = "Content 1", timeStamp = 123L),
            Notes(id = 2, title = "Note 2", content = "Content 2", timeStamp = 124L)
        )

        coEvery { repository.getNotes() } returns flowOf(notesList)

        val result = getNotesUseCase("Nonexistent")

        result.first().let { notes ->
            assertEquals(0, notes.size)
        }
    }
}