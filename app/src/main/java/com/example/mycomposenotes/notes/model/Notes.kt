package com.example.mycomposenotes.notes.model

data class Notes(
    val id: String,
    val title: String,
    val content: String,
    val date: String,
    val category: String,
    val mediaId: String,
    val backGroundImageId: Int
)
