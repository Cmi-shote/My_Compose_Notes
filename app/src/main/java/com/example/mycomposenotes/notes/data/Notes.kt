package com.example.mycomposenotes.notes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val title: String,
    val content: String?,
    val date: String?,
    val category: String?,
    val mediaId: String?,
    val backGroundImageId: Int?
)
