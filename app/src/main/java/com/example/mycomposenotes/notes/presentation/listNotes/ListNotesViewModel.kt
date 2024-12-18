package com.example.mycomposenotes.notes.presentation.listNotes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.domain.useCase.NotesUseCases
import com.example.mycomposenotes.notes.domain.useCase.SignOutUseCase
import com.example.mycomposenotes.notes.presentation.login.AuthenticationState
import com.example.mycomposenotes.notes.presentation.welcome.SearchWidgetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListNotesViewModel(
    private val useCases: NotesUseCases,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _notesList = MutableStateFlow<List<Notes>>(emptyList())
    val notesList: StateFlow<List<Notes>> = _notesList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _authenticationState = mutableStateOf<AuthenticationState>(AuthenticationState.Unauthenticated)
    val authenticationState: State<AuthenticationState> = _authenticationState

    init {
        getNotes()
    }

    fun onEvent(event: ListNotesEvent) {
        when (event) {
            is ListNotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNotesUseCase(event.note)
                    // Refresh notes after deletion
                    getNotes(_searchQuery.value)
                }
            }
            is ListNotesEvent.SearchNotes -> {
                _searchQuery.value = event.query
                getNotes(event.query)
            }
            is ListNotesEvent.SearchWidget -> {
                _searchWidgetState.value = event.widget
            }
            is ListNotesEvent.SignOut -> {
                signOut()
            }
        }
    }

    /**
     * Fetches notes from the use case and updates the notes list.
     *
     * @param query The search query to filter notes.
     */
    private fun getNotes(query: String = "") {
        viewModelScope.launch {
            useCases.getNotesUseCase(query).collectLatest { notes ->
                _notesList.value = notes
            }
        }
    }

    private fun signOut() {
        signOutUseCase.execute{
            _authenticationState.value = it
        }
    }
}
