package com.example.mycomposenotes.notes.presentation.navigation

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
    data class AddEditRoute(val noteId: Int?) : Route
}