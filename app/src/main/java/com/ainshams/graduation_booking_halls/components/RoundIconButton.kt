package com.ainshams.graduation_booking_halls.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconButtonSizeModifier = Modifier.size(40.dp)

@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    imageVictor: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color(0xff15420F ),
    backgroundColor: Color = Color(0xffF6F6F6),
    elevation: Dp = 4.dp
) {

    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .clickable { onClick() }
            .then(IconButtonSizeModifier),
        shape = CircleShape,
        backgroundColor = backgroundColor,
        elevation = elevation,

        ) {
        Icon(imageVector = imageVictor, contentDescription = "plus or minus icon",tint=tint)

    }

}