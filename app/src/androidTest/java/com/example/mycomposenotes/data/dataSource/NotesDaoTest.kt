package com.example.mycomposenotes.data.dataSource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mycomposenotes.notes.data.dataSource.NotesDao
import com.example.mycomposenotes.notes.data.database.NotesDatabase
import com.example.mycomposenotes.notes.domain.model.Notes
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesDaoTest {
    private lateinit var database: NotesDatabase
    private lateinit var dao: NotesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = database.notesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertNote_returnsTrue() = runTest {
        val note = Notes(id = 123421, title = "A Right Media", content = "Mix Can Make The Difference", timeStamp = System.currentTimeMillis(), category = "Work", mediaId = "1", backGroundImageId = 2)
        dao.insertData(note)

        val notes = dao.getNotes().first()
        assert(notes.contains(note))

    }

    @Test
    fun deleteNote_returnsFalse() = runTest {
        val note = Notes(id = 123421, title = "A Right Media", content = "Mix Can Make The Difference", timeStamp = System.currentTimeMillis(), category = "Work", mediaId = "1", backGroundImageId = 2)
        dao.insertData(note)
        dao.deleteData(note)
        val notes = dao.getNotes().first()
        assertFalse(notes.contains(note))
    }

    @Test
    fun updateNote_returnsTrue() = runTest {
        val note = Notes(id = 123421, title = "A Right Media", content = "Mix Can Make The Difference", timeStamp = System.currentTimeMillis(), category = "Work", mediaId = "1", backGroundImageId = 2)
        dao.insertData(note)
        val updatedNote = note.copy(title = "A Right Media Mix Can Make The Difference")
        dao.updateData(updatedNote)
        val retrievedNote = dao.getNoteById(123421)
        assertThat(retrievedNote?.title).isEqualTo("A Right Media Mix Can Make The Difference")
    }

    @Test
    fun getNoteByTimeStamp_returnsCorrectNote() = runTest {
        val timeStamp = System.currentTimeMillis()
        val note = Notes(id = 1, "Title", content = "Content", timeStamp = timeStamp, category = "Work", backGroundImageId = 2)
        dao.insertData(note)

        val retrievedNote = dao.getNoteByTimeStamp(timeStamp)
        assert(retrievedNote.timeStamp == timeStamp)
    }

}