package com.example.mycomposenotes.notes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.listNotes.ListNotesScreen
import com.example.mycomposenotes.notes.presentation.login.LoginScreen
import com.example.mycomposenotes.notes.presentation.noteDetails.AddEditNotesScreen
import com.example.mycomposenotes.notes.presentation.signup.SignupScreen
import com.example.mycomposenotes.notes.presentation.welcome.WelcomeScreen

@Composable
fun NotesNavHost(navController: NavHostController, startDestination: Route) {
    NavHost(navController = navController, startDestination = startDestination) {
        // Welcome Screen
        composable<Route.WelcomeRoute> {
            WelcomeScreen(
                onClick = {
                    navController.navigate(
                        Route.SignupRoute
                    )
                }
            )
        }

        // Login Screen
        composable<Route.LoginRoute> {
            LoginScreen(
                onClick = {
                    navController.navigate(
                        Route.SignupRoute
                    )
                },
                onSuccess = {
                    navController.navigate(
                        Route.ListNotesRoute
                    )
                }
            )
        }

        // Signup Screen
        composable<Route.SignupRoute> {
            SignupScreen(
                onClick = {
                    navController.navigate(
                        Route.LoginRoute
                    )
                },
                onSuccess = {
                    navController.navigate(
                        Route.ListNotesRoute
                    )
                }
            )
        }

        // List Notes Screen
        composable<Route.ListNotesRoute> {
            ListNotesScreen(
                onCardSelected = { note ->
                    navController.navigate(
                        Route.AddEditRoute(note.id)
                    )
                },
                onclick = {
                    navController.navigate(
                        Route.AddEditRoute(Notes().id)
                    )
                },
                onSignOut = {
                    navController.navigate(Route.WelcomeRoute) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Add/Edit Notes Screen
        composable<Route.AddEditRoute>
//            typeMap = mapOf(
//                typeOf<Notes>() to CustomNavType.NoteType
//            )
         {
            val arguments = it.toRoute<Route.AddEditRoute>()
            AddEditNotesScreen(
                noteId = arguments.noteId,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}