package com.example.mycomposenotes.notes.presentation.navigation

import com.example.mycomposenotes.notes.domain.model.Notes
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object WelcomeRoute : Route

    @Serializable
    data object LoginRoute : Route

    @Serializable
    data object SignupRoute : Route

    @Serializable
    data object ListNotesRoute : Route

    @Serializable
    data class AddEditRoute(val note: Notes) : Route
}