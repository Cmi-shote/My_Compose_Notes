package com.example.mycomposenotes.notes.data.repository

import android.util.Log
import com.example.mycomposenotes.notes.data.dataSource.NotesDao
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.repository.NotesRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class NotesRepoImpl(
    private val notesDao: NotesDao,
    private val firebaseFirestore: FirebaseFirestore
) : NotesRepo {
    override fun getNotes(): Flow<List<Notes>> {
        return notesDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Notes? {
        return notesDao.getNoteById(id)
    }

    override suspend fun insertData(notes: Notes) {
        return notesDao.insertData(notes)
    }

    override suspend fun deleteData(notes: Notes) {
        return notesDao.deleteData(notes)
    }

    override suspend fun updateData(notes: Notes) {
        return notesDao.updateData(notes)
    }

    override suspend fun fetchNotesFromFirebase(): List<Notes> {
        return try {
            val documents = firebaseFirestore.collection("notes").get().await()
            documents.map { doc ->
                Notes(
                    id = doc.getLong("id")?.toInt(),
                    title = doc.getString("title") ?: "",
                    content = doc.getString("content") ?: "",
                    category = doc.getString("category") ?: "",
                    backGroundImageId = doc.getLong("backGroundImageId")?.toInt() ?: 0,
                    timeStamp = doc.getLong("timeStamp") ?: 0L,
                    mediaId = doc.getString("mediaId") ?: ""
                )
            }
        } catch (e: Exception) {
            Log.e("NotesRepoImpl", "Error fetching notes from Firebase", e)
            emptyList()
        }
    }

    override suspend fun uploadNoteToFirebase(notes: Notes) {
        try {
            val noteData = hashMapOf(
                "id" to notes.id,
                "title" to notes.title,
                "content" to notes.content,
                "category" to notes.category,
                "backGroundImageId" to notes.backGroundImageId,
                "timeStamp" to notes.timeStamp,
                "mediaId" to notes.mediaId
            )

            firebaseFirestore.collection("notes")
                .document(notes.id.toString())
                .set(noteData)
                .await()

            Log.d("NotesRepoImpl", "Note uploaded successfully to Firebase")
        } catch (e: Exception) {
            Log.e("NotesRepoImpl", "Error uploading note to Firebase", e)
        }
    }

    override suspend fun getNoteByTimeStamp(timeStamp: Long): Notes {
        return notesDao.getNoteByTimeStamp(timeStamp)
    }
}