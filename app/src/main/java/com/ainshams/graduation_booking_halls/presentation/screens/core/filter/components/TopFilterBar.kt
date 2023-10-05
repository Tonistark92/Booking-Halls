package com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopFilterBar(
    onPutCredentialsClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {
        Text(
            text = "Find Available Halls",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        IconButton(
            onClick = onPutCredentialsClicked,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = "",
                    tint = Color(0Xff00E6B3)
                )
                Text(text = "Put Credentials", color = Color.White)
            }
        }
    }
}