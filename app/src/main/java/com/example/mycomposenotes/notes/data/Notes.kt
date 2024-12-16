package com.example.mycomposenotes.notes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycomposenotes.utils.Constants.NOTE_TABLE_NAME

@Entity(tableName = NOTE_TABLE_NAME)
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
