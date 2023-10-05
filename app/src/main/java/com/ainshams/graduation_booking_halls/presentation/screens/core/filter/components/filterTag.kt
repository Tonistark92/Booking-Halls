package com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FilterTag(
    title: String,
    value: String,

) {
    Box(
        modifier = Modifier.width(150.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
            Text(
                text = value,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

        }
    }
}