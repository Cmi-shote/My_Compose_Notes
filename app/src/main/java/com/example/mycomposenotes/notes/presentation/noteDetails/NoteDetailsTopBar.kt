package com.example.mycomposenotes.notes.presentation.noteDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.ui.theme.MyComposeNotesTheme

@Composable
fun NoteDetailsTopBar(
    modifier: Modifier = Modifier,
    buttonText: String = "Save",
    onDoneBtnClick: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
    onClipClicked: () -> Unit = {},
) {
    var showMoreOptions by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .statusBarsPadding()
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CircularIconButton(
            icon = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back Arrow",
            onClick = onBackPressed
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Button(
                onClick = onDoneBtnClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 15.dp),
                modifier = Modifier.padding(end = 8.dp).height(30.dp)
            ) {
                Text(
                    text = buttonText,
                    fontSize = 12.sp,
                )
            }

            CircularIconButton(
                icon = Icons.Default.MoreVert,
                contentDescription = "More Options",
                onClick = { showMoreOptions = !showMoreOptions }
            )

            DropdownMenu(
                expanded = showMoreOptions,
                onDismissRequest = { showMoreOptions = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = {
                        onDeleteClicked()
                        showMoreOptions = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                )

                DropdownMenuItem(
                    text = { Text("Clip") },
                    onClick = {
                        onClipClicked()
                        showMoreOptions = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.clip_icon),
                            contentDescription = "Clip"
                        )
                    }
                )

//                DropdownMenuItem(
//                    text = { Text("Camera") },
//                    onClick = {
//                        onCameraClicked()
//                        showMoreOptions = false
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = ImageVector.vectorResource(R.drawable.camera_icon),
//                            contentDescription = "Camera"
//                        )
//                    }
//                )
            }
        }
    }
}

@Composable
fun CircularIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            ), // Slight transparency
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TransparentTopBarPreview() {
    MyComposeNotesTheme {

            NoteDetailsTopBar(
                onBackPressed = {},
                onDeleteClicked = {},
                onClipClicked = {},
            )
    }
}