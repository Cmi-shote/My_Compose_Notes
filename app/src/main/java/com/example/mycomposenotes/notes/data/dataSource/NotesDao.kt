package com.example.mycomposenotes.notes.data.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycomposenotes.notes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    suspend fun getNoteById(id: Int): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(notes: Notes)

    @Delete
    suspend fun deleteData(notes: Notes)

    @Update
    suspend fun updateData(notes: Notes)

    @Query("SELECT * FROM notes_table WHERE timeStamp like :timeStamp")
    suspend fun getNoteByTimeStamp(timeStamp: Long): Notes
}