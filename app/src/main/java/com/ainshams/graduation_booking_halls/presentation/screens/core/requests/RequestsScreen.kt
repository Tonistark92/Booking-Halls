package com.ainshams.graduation_booking_halls.presentation.screens.core.requests

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.RequestsDto
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun RequestsScreen(
    onItemClick: (
//    id: Int, start: LocalTime, end: LocalTime, date: LocalDate
    ) -> Unit
) {

    val viewModle: RquestsScreensViewModle = hiltViewModel()
    val state = viewModle.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModle.state.isRefreshing
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xff15420F ), strokeWidth = 4.dp)
        } else if (state.networkError || state.notFound) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Network Error ")
                Spacer(modifier = Modifier.height(200.dp))
            }
        } else if (state.Requests.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LoaderForLotti2()
            }
        } else if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xff15420F), strokeWidth = 4.dp)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFECECEC))
                    .padding(bottom = 55.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 20.dp)
                        .background(Color.White),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Requests",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                val l = listOf<String>("DELETE")
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModle.refreshing()
                    },

                    ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.Requests.size) { item ->
//                            HallRowBooking(request = state.Requests[it]) {
//
//                            }
                            MyItemWithMenue(state.Requests[item], l, Modifier) { menue ->
                                viewModle.deleteReq(state.Requests[item].request_num_id)
                                viewModle.refreshing()
                            }
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun HallRowBooking(request: RequestsDto, onItemClick: (RequestsDto) -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            //.height(150.dp)
            .clickable {
                onItemClick(request)
            },
        backgroundColor = Color.White,
        shape = RectangleShape,
        elevation = 10.dp
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize(),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .background(
                            if (request.respond_state == 0) {
                                Color(android.graphics.Color.parseColor("#9A8CF0"))

                            } else if (request.respond_state == -1) {
                                Color(android.graphics.Color.parseColor("#FF1F1F"))

                            } else {
                                Color(android.graphics.Color.parseColor("#9AB107"))

                            }
                        )
                        .clip(shape = RoundedCornerShape(50.dp))
                ) {}
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = if (request.respond_state == 0) {
                        "Waiting"
                    } else if (request.respond_state == -1) {
                        " Request Denied"
                    } else {
                        "Accepted"
                    },
                    style = TextStyle(
                        color = if (request.respond_state == 0) {
                            Color(android.graphics.Color.parseColor("#9A8CF0"))
                        } else if (request.respond_state == -1) {
                            Color(android.graphics.Color.parseColor("#FF1F1F"))
                        } else {
                            Color(android.graphics.Color.parseColor("#9AB107"))
                        }
                    ),
                    fontSize = 16.sp
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Date : ")
                }

                // Second text - bold with 15.sp size
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(request.date_time_send.toString())
                }
            })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Start Time : ")
                }

                // Second text - bold with 15.sp size
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(request.start_time_booking.toString())
                }
            })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("End Time : ")
                }

                // Second text - bold with 15.sp size
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(request.end_time_booking.toString())
                }
            })
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Note : ", color = Color.Black)
                Text(
                    text = "Text: ${request.text}",
                    style = TextStyle(color = Color.Black),
                    fontSize = 16.sp
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun LoaderForLotti2() {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.data3))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )

    val color by derivedStateOf { Color.Red }

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR, value = color.toArgb(), keyPath = arrayOf(
                "compass needle", "Shape 1", "Fill 1"
            )
        ),

        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR, value = color.toArgb(), keyPath = arrayOf(
                "donut", "Group 1", "Fill 1"
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.OPACITY, value = 50, keyPath = arrayOf(
                "compass needle", "Shape 1", "Fill 1"
            )
        ),
    )

    LottieAnimation(
        compositionResult.value,
        progress,
        dynamicProperties = dynamicProperties,
        modifier = Modifier.padding(all = 20.dp)
    )

}

@Composable
fun MyItemWithMenue(
    request: RequestsDto,
    dropdownItems: List<String>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
//    onCardClick: (RequestsDto) -> Unit = {},
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val density = LocalDensity.current

    Card(
//        elevation = 4.dp,
        modifier = modifier.padding(10.dp)
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
        backgroundColor = Color.White,
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    )
                },
            contentAlignment = Alignment.Center
//                .padding(16.dp)
        ) {




                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize(),

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(
                                    if (request.respond_state == 0) {
                                        Color(android.graphics.Color.parseColor("#9A8CF0"))

                                    } else if (request.respond_state == -1) {
                                        Color(android.graphics.Color.parseColor("#FF1F1F"))

                                    } else {
                                        Color(android.graphics.Color.parseColor("#9AB107"))

                                    }
                                )
                                .clip(shape = RoundedCornerShape(50.dp))
                        ) {}
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = if (request.respond_state == 0) {
                                "Waiting"
                            } else if (request.respond_state == -1) {
                                " Request Denied"
                            } else {
                                "Accepted"
                            },
                            style = TextStyle(
                                color = if (request.respond_state == 0) {
                                    Color(android.graphics.Color.parseColor("#9A8CF0"))
                                } else if (request.respond_state == -1) {
                                    Color(android.graphics.Color.parseColor("#FF1F1F"))
                                } else {
                                    Color(android.graphics.Color.parseColor("#9AB107"))
                                }
                            ),
                            fontSize = 16.sp
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = buildAnnotatedString {
                        // First text - normal
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Date : ")
                        }

                        // Second text - bold with 15.sp size
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        ) {
                            append(request.date_time_send.toString())
                        }
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = buildAnnotatedString {
                        // First text - normal
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Start Time : ")
                        }

                        // Second text - bold with 15.sp size
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        ) {
                            append(request.start_time_booking.toString())
                        }
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = buildAnnotatedString {
                        // First text - normal
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("End Time : ")
                        }

                        // Second text - bold with 15.sp size
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        ) {
                            append(request.end_time_booking.toString())
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Note : ", color = Color.Black)
                        Text(
                            text = "Text: ${request.text}",
                            style = TextStyle(color = Color.Black),
                            fontSize = 16.sp
                        )
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))




        }
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = {
                isContextMenuVisible = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropdownItems.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemClick(it)
                        isContextMenuVisible = false
                    },
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(text = it, color = Color.Black)
                }
            }
        }
    }
}

