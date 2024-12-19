package com.example.mycomposenotes.notes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.mycomposenotes.notes.presentation.navigation.NotesNavHost
import com.example.mycomposenotes.notes.presentation.navigation.Route
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeNotesTheme {
                val navController = rememberNavController()

                // Determine start destination based on authentication state
                val startDestination = if (firebaseAuth.currentUser != null) {
                    Route.ListNotesRoute // Navigate to notes list if authenticated
                } else {
                    Route.WelcomeRoute// Otherwise, navigate to welcome screen
                }

                NotesNavHost(
                    navController = navController,
                    startDestination = startDestination,
                )
            }
        }
    }
}