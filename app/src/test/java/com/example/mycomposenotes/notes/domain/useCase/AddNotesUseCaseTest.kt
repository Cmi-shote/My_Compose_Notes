package com.example.mycomposenotes.notes.domain.useCase

import com.example.mycomposenotes.notes.domain.model.InvalidNoteException
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class AddNotesUseCaseTest {
    private val repository = mockk<NotesRepo>(relaxed = true) // Relaxed mock to avoid unimplemented behavior
    private val addNotesUseCase = AddNotesUseCase(repository)

    @Test
    fun addNote_withValidNote_insertsNoteIntoRepository() = runTest {
        val notesToInsert = Notes(
            id = 1,
            title = "Test Title",
            content = "Test Content",
            timeStamp = System.currentTimeMillis()
        )

        addNotesUseCase(notesToInsert)

        // Verify that the repository's insertData method is called with the correct note
        coVerify { repository.insertData(notesToInsert) }
    }

    @Test
    fun addNote_withBlankTitle_throwsInvalidNoteException() = runTest {
        val invalidNote = Notes(
            id = 2,
            title = "",
            content = "Content",
            timeStamp = System.currentTimeMillis()
        )

        val exception = assertFailsWith<InvalidNoteException> {
            addNotesUseCase(invalidNote)
        }

        assertEquals("Note title cannot be blank", exception.message)

        // Verify that insertData is not called
        coVerify(exactly = 0) { repository.insertData(any()) }
    }

    @Test
    fun addNote_withBlankContent_throwsInvalidNoteException() = runTest {
        val invalidNote = Notes(
            id = 3,
            title = "Title",
            content = "",
            timeStamp = System.currentTimeMillis()
        )

        val exception = assertFailsWith<InvalidNoteException> {
            addNotesUseCase(invalidNote)
        }

        assertEquals("Note content cannot be blank", exception.message)

        // Verify that insertData is not called
        coVerify(exactly = 0) { repository.insertData(any()) }
    }

    @Test
    fun addNote_withRepositoryError_handlesException() = runTest {
        val note = Notes(
            id = 4,
            title = "Title",
            content = "Content",
            timeStamp = System.currentTimeMillis()
        )

        coEvery { repository.insertData(note) } throws Exception("Repository error")

        assertFailsWith<Exception> {
            addNotesUseCase(note)
        }

        coVerify { repository.insertData(note) }
    }
}