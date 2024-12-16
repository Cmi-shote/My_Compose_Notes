package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.data.Notes
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme

@Composable
fun NoteContent(
    modifier: Modifier = Modifier,
    note: Notes?,
    onTitleChange: (String) -> Unit = {},
    onContentChange: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onCameraClicked: () -> Unit = {},
    onClipClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {

    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            note?.backGroundImageId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.matchParentSize(),
                    alpha = 0.2f
                )
            }

            NoteDetailsTopBar(
                onBackPressed = onBackPressed,
                onCameraClicked = onCameraClicked,
                onDeleteClicked = onDeleteClicked,
                onClipClicked = onClipClicked
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "Clock Icon",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "21/02",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (note == null) {
                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        onTitleChange(it)
                    },
                    label = { Text("Title") },
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorResource(R.color.off_white),
                        focusedContainerColor = colorResource(R.color.off_white),
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (note == null) {
                TextField(
                    value = content,
                    onValueChange = {
                        content = it
                        onContentChange(it)
                    },
                    label = { Text("Content") },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorResource(R.color.off_white),
                        focusedContainerColor = colorResource(R.color.off_white),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NoteDetailsScreenContentPreview() {
    MyComposeNotesTheme {
        val noteSample = Notes(
            id = "110en2323",
            title = "A Right Media Mix Can Make The Difference",
            content = stringResource(R.string.lorem_ipsum),
            date = "13/24",
            category = "Work",
            mediaId = "1",
            backGroundImageId = R.drawable.rainbow_6
        )
        NoteContent(note = null)
    }
}