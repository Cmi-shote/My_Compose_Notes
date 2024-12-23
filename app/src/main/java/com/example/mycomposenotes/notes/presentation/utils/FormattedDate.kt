package com.example.mycomposenotes.notes.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.toFormattedDate(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd MMMM yyyy, hh:mm a", Locale.getDefault())
    return formatter.format(date)
}