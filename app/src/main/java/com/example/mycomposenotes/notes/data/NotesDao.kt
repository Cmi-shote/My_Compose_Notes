package com.example.mycomposenotes.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNoteById(id: String): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(notes: Notes)

    @Delete
    suspend fun deleteData(notes: Notes)

    @Update
    suspend fun updateData(notes: Notes)
}