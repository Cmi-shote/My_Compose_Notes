package com.example.mycomposenotes.notes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.listNotes.ListNotesScreen
import com.example.mycomposenotes.notes.presentation.login.LoginScreen
import com.example.mycomposenotes.notes.presentation.noteDetails.AddEditNotesScreen
import com.example.mycomposenotes.notes.presentation.signup.SignupScreen
import com.example.mycomposenotes.notes.presentation.utils.CustomNavType
import com.example.mycomposenotes.notes.presentation.welcome.WelcomeScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun NotesNavHost(navController: NavHostController, startDestination: Any) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<WelcomeRoute> {
            WelcomeScreen(
                onClick = {
                    navController.navigate(
                        SignupRoute
                    )
                }
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                onClick = {
                    navController.navigate(
                        SignupRoute
                    )
                },
                onSuccess = {
                    navController.navigate(
                        ListNotesRoute
                    )
                }
            )
        }

        // Welcome Screen
        composable<SignupRoute> {
            SignupScreen(
                onClick = {
                    navController.navigate(
                        LoginRoute
                    )
                },
                onSuccess = {
                    navController.navigate(
                        ListNotesRoute
                    )
                }
            )
        }

        // List Notes Screen
        composable<ListNotesRoute> {
            ListNotesScreen(
                onCardSelected = { note ->
                    navController.navigate(
                        AddEditRoute(note)
                    )
                },
                onclick = {
                    navController.navigate(
                        AddEditRoute(Notes())
                    )
                },
                onSignOut = {

                }
            )
        }

        // Add/Edit Notes Screen
        composable<AddEditRoute>(
            typeMap = mapOf(
                typeOf<Notes>() to CustomNavType.NoteType
            )
        ) {
            val arguments = it.toRoute<AddEditRoute>()
            AddEditNotesScreen(
                note = arguments.note,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}


@Serializable
data object ListNotesRoute

@Serializable
data class AddEditRoute(
    val note: Notes
)

@Serializable
data object WelcomeRoute

@Serializable
data object LoginRoute

@Serializable
data object SignupRoute