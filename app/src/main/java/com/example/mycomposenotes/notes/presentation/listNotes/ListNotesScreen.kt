package com.example.mycomposenotes.notes.presentation.listNotes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.welcome.MainAppBar
import com.example.mycomposenotes.notes.presentation.welcome.SearchWidgetState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListNotesScreen(
    modifier: Modifier = Modifier,
    onclick: () -> Unit = {},
    onSignOut: () -> Unit = {},
    onCardSelected: (Notes) -> Unit = {},
    viewModel: ListNotesViewModel = koinViewModel<ListNotesViewModel>()
) {

    val notesList by viewModel.notesList.collectAsStateWithLifecycle()
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchQuery.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MainAppBar(
                notes = notesList,
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.onEvent(ListNotesEvent.SearchNotes(it))
                },
                onSearchTriggered = {
                    viewModel.onEvent(ListNotesEvent.SearchWidget(SearchWidgetState.OPENED))
                },
                onCloseClicked =  {
                    viewModel.onEvent(ListNotesEvent.SearchNotes(""))
                    viewModel.onEvent(ListNotesEvent.SearchWidget(SearchWidgetState.CLOSED))
                },
                onSearchClicked = {
                    Log.d("ListNotesScreen", "onSearchClicked: $it")
                },
                onSignOut = {
                    viewModel.onEvent(ListNotesEvent.SignOut)
                    onSignOut()
                }
            )
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onclick()
            }, containerColor = Color.Black, contentColor = Color.White) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            if (isLoading) {
                // Show CircularProgressIndicator while loading
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Black
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            start = paddingValues.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp,
                            top = paddingValues.calculateTopPadding(),
                            end = paddingValues.calculateRightPadding(LayoutDirection.Ltr) + 16.dp,
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(notesList) { note ->
                        NoteCard(
                            note = note,
                            onClick = { onCardSelected(note) },
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    )
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun ListNotesScreenPreview() {
//    MyComposeNotesTheme {
//        ListNotesScreen(
//            notes = (1..100).map {
//                (Notes(
//                    id = 123421,
//                    title = "A Right Media Mix Can Make The Difference",
//                    content = stringResource(R.string.lorem_ipsum),
//                    timeStamp = System.currentTimeMillis(),
//                    category = "Work",
//                    mediaId = "1",
//                    backGroundImageId = R.drawable.note_background_1
//                )).copy(id = it)
//            }
//        )
//    }
//}