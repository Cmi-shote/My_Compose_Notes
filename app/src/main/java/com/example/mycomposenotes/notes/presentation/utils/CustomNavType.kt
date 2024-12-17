package com.example.mycomposenotes.notes.presentation.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.mycomposenotes.notes.domain.model.Notes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val NoteType = object : NavType<Notes>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Notes? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Notes {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Notes): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Notes) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }

}