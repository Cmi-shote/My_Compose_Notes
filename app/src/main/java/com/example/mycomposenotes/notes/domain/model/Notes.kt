package com.example.mycomposenotes.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.presentation.utils.Constants.NOTE_TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = NOTE_TABLE_NAME)
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val category: String = "",
    val backGroundImageId: Int = 0,
    val timeStamp: Long = 0L,
    val mediaId: String = ""
) {
    companion object {
        val noteBackgroundImages = listOf(
            R.drawable.note_background_1,
            R.drawable.note_background_2,
            R.drawable.note_background_3,
            R.drawable.note_background_4,
            R.drawable.note_background_5,
            R.drawable.note_background_6,
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)