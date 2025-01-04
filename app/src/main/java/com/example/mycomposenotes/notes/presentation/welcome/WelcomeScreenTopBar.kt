package com.example.mycomposenotes.notes.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposenotes.R
import com.example.mycomposenotes.notes.domain.model.Notes

@Composable
fun WelcomeScreenTopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onSignOut: () -> Unit = {},
    notes: List<Notes>? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .statusBarsPadding()
    ) {
        Row {
            Box(modifier = Modifier
                .size(20.dp)) {
                Text(
                    text = "N",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(start = 3.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.notey),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (notes != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onSignOut,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    modifier = Modifier.padding(end = 8.dp).height(30.dp)
                ) {
                    Text(
                        text = stringResource(R.string.sign_out),
                        fontSize = 12.sp,
                    )
                }

                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun WelcomeScreenTopBarPreview() {
    WelcomeScreenTopBar(
        notes = listOf(Notes())
    )
}