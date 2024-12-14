package com.example.mycomposenotes.notes.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposenotes.R

@Composable
fun WelcomeScreenContent(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.all_notes_organized),
            fontSize = 55.sp,
            lineHeight = 80.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.all_notes_organized_desc),
            fontSize = 20.sp,
            modifier = Modifier.width(300.dp),
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}