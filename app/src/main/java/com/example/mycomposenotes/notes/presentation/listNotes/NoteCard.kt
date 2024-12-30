package com.example.mycomposenotes.notes.presentation.listNotes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.domain.model.Notes
import com.example.mycomposenotes.notes.presentation.utils.toFormattedDate
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Notes,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Column takes 75% of the width
            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(16.dp)
            ) {
                Text(
                    text = note.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                        contentDescription = "Clock Icon",
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = note.timeStamp.toFormattedDate(),
                        fontSize = 10.sp
                    )

                    Text(
                        text = note.category,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Image(
                painter = painterResource(Notes.noteBackgroundImages[note.backGroundImageId]!!),
                contentDescription = "Note background",
                modifier = Modifier
                    .weight(0.25f)
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alpha = 0.1f
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteCardPreview() {
    MyComposeNotesTheme {
        val noteSample = Notes(
            id = 1,
            title = "Sample Note Title",
            content = "Sample content",
            timeStamp = System.currentTimeMillis(),
            category = "Work",
            mediaId = "1",
            backGroundImageId = R.drawable.note_background_1 // Use a placeholder
        )
        NoteCard(note = noteSample)
    }
}
