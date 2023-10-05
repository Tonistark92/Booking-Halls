package com.ainshams.graduation_booking_halls.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ainshams.graduation_booking_halls.R
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp,
    work:Boolean=true
) {
    val animatState = remember {
        mutableStateOf(true)
    }
    if(animatState.value){val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

        circles.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            0.0f at 0 with LinearOutSlowInEasing
                            1.0f at 300 with LinearOutSlowInEasing
                            0.0f at 600 with LinearOutSlowInEasing
                            0.0f at 1200 with LinearOutSlowInEasing
                        },

                        )


                )
            }
        }

        val circleValues = circles.map { it.value }
        val distance = with(LocalDensity.current) { travelDistance.toPx() }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            circleValues.forEach { value ->
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .graphicsLayer {
                            translationY = -value * distance
                        }
                        .background(
                            color = circleColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }

}
@Composable
private fun LoaderForLotti() {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.data))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )

    val color by remember {

    derivedStateOf { Color.Red }
    }


    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),

        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "donut",
                "Group 1",
                "Fill 1"
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.OPACITY,
            value = 50,
            keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),
    )

    LottieAnimation(
        compositionResult.value,
        progress,
        dynamicProperties = dynamicProperties,
        modifier = Modifier.padding(all = 50.dp)
    )

}
