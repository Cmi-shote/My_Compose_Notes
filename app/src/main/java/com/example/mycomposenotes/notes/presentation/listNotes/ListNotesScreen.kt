package com.example.mycomposenotes.notes.presentation.listNotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.model.Notes
import com.example.mycomposenotes.notes.presentation.welcome.WelcomeScreenTopBar
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme

@Composable
fun ListNotesScreen(modifier: Modifier = Modifier, notes: List<Notes>) {

    Scaffold(
        topBar = {
            WelcomeScreenTopBar(onClick = {}, notes = notes)
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(onClick = {}, containerColor = Color.Black, contentColor = Color.White) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
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
                items(notes) { note ->
                    NoteCard(
                        note = note,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ListNotesScreenPreview() {
    MyComposeNotesTheme {
        ListNotesScreen(
            notes = (1..100).map {
                (Notes(
                    id = "110en2323",
                    title = "A Right Media Mix Can Make The Difference",
                    content = stringResource(R.string.lorem_ipsum),
                    date = "13/24",
                    category = "Work",
                    mediaId = "1",
                    backGroundImageId = R.drawable.rainbow_1
                )).copy(id = it.toString())
            }
        )
    }
}