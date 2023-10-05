package com.ainshams.graduation_booking_halls.presentation.screens.core.details

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.navigation.graphs.Graph
import com.ainshams.graduation_booking_halls.presentation.screens.core.details.components.FacilityTag
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login.bio.Biometric
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.*
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonHighlightAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewDetailsScreen(id: Int) {
    val viewModle: DetailsViewModle = hiltViewModel()
    val state = viewModle.state
    viewModle.setId(id)

    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setArrowPosition(0.5f)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(12)
        setCornerRadius(8f)
        setBackgroundColorResource(R.color.mygreen)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setIsVisibleOverlay(true)
        setOverlayPaddingResource(R.dimen.tool_tip)
        setBalloonHighlightAnimation(BalloonHighlightAnimation.BREATH)
        setOverlayShape(
            BalloonOverlayRoundRect(
                R.dimen.tool_tip,
                R.dimen.tool_tip
            )
        )
        setDismissWhenClicked(true)
    }
    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF6F6F6)), contentAlignment = Alignment.Center
    ) {
        if (state.notFound || state.networkError) {


        } else if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xff15420F), strokeWidth = 4.dp)
        } else {
            Column(
                modifier = Modifier
//                .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(Color(0xffF6F6F6)),
                horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center

            ) {
                HorizontalPager(
                    state = pagerState,
                    count = 2,
                    modifier = Modifier
//                    .weight(1f)
                        .padding(0.dp, 10.dp, 0.dp, 10.dp)
                ) { page ->
                    Card(modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page.absoluteValue)
//                            lerp(
//                                    start = 0.85f,
//                                    stop = 1f,
//                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                                )
//                                .also {
//                                    scaleX = it
//                                    scaleY = it
//                                }
                        }
                        .fillMaxWidth(),
                        shape = RectangleShape

                    ) {
//                        "https://booking-apis.larasci.site/" +state.hall?.photos?.get(0)
                        val painter1 = rememberImagePainter(
                            "https://booking-apis.larasci.site/" + state.hall?.photos!!.get(0)
                           ,
                            builder = {
                                crossfade(1000)
                            }
                        )
                        val painter2 = rememberImagePainter(
                            "https://booking-apis.larasci.site/" + state.hall?.photos!!.get(1)
                            ,
                            builder = {
                                crossfade(1000)
                            }
                        )

                        Box(contentAlignment = Alignment.BottomCenter) {
                            Image(
                                painter =
                                     when (page) {
                                        0 -> painter1
                                        1 -> painter2
                                        else ->painter1
                                    }
                                ,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                activeColor = Color(android.graphics.Color.parseColor("#E2AC80")),
                                inactiveColor = Color.DarkGray,
                                spacing = 10.dp,
                                indicatorWidth = 10.dp,
                                indicatorHeight = 10.dp
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                }
                val scrollState = rememberScrollState()

                LaunchedEffect(Unit) {
                    // Scroll to last item
                    scrollState.scrollTo(scrollState.maxValue)
                }
                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = state.hall?.hall_data?.hall_name!!,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.hall?.hall_data?.description_place!!,
                        fontSize = 30.sp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = "Capacity  ",
                            modifier = Modifier
                                .weight(5f)
                                .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                            color = Color.Black
                        )
                        Text(
                            text = state.hall?.hall_data?.capacity.toString(),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f),
                            color = Color(0xff15420F),
                            textAlign = TextAlign.End

                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Type  ",
                            modifier = Modifier
                                .weight(5f)
                                .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                            color = Color.Black
                        )
                        Text(
                            text = state.hall?.hall_data?.type!!,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f)
                                .padding(end = 10.dp),
                            color = Color(0xff15420F),
                            textAlign = TextAlign.End

                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Location  ",
                            modifier = Modifier
                                .weight(5f)
                                .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                            color = Color.Black
                        )
                        Text(
                            text = state.hall?.hall_data?.floor_place.toString(),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f)
                                .padding(end = 10.dp),
                            color = Color(0xff15420F),
                            textAlign = TextAlign.End

                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Building Place  ",
                            modifier = Modifier
                                .weight(5f)
                                .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                            color = Color.Black
                        )
                        Text(
                            text = state.hall?.hall_data?.building_place.toString(),
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f)
                                .padding(end = 10.dp),
                            color = Color(0xff15420F),
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Facilities",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state.hall!!.hall_data.has_monitor == 1) {

                            FacilityTag(tag = " Monitor")
                        }
                        if (state.hall!!.hall_data.has_air_condition == 1) {

                            FacilityTag(tag = " Air Conditioner")
                        }
                        if (state.hall!!.hall_data.has_projector == 1) {

                            FacilityTag(tag = "Projector")
                        }
                        if (state.hall!!.hall_data.is_special == 1) {

                            FacilityTag(tag = "Special")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = state.textComplian.value,
                            onValueChange = { state.textComplian.value = it },
                            label = { Text("Put a reason") },
                            placeholder = { Text("Type here a Reason ") },
                            maxLines = 10, // Set the maximum number of lines
                            textStyle = TextStyle(color = Color.Black), // Set the text style
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, // Set the text color
                                backgroundColor = Color.White, // Set the background color
                                cursorColor = Color(0xff15420F), // Set the cursor color
                                focusedBorderColor = Color(0xff15420F), // Set the focused border color
                                focusedLabelColor = Color.Black, // Set the focused label color
                                unfocusedBorderColor = Color(0xff15420F), // Set the unfocused border color
                                unfocusedLabelColor = Color.Black // Set the unfocused label color
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Balloon(
                        builder = builder,
                        balloonContent = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp),
                                text = "You Can Submit Complain from Here",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    ) { balloonWindow ->
                            balloonWindow.showAlignTop()


                        Button(
                            onClick = {
                                viewModle.pushComplain()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff15420F),
                                contentColor = Color.Black
                            ),
                        ) {
                            Text(
                                text = "Send Your Complain", color = Color.Black
                            )

                        }


                    }



                }

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Facilities",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state.hall!!.hall_data.has_monitor == 1) {

                            FacilityTag(tag = " Monitor")
                        }
                        if (state.hall!!.hall_data.has_air_condition == 1) {

                            FacilityTag(tag = " Air Conditioner")
                        }
                        if (state.hall!!.hall_data.has_projector == 1) {

                            FacilityTag(tag = "Projector")
                        }
                        if (state.hall!!.hall_data.is_special == 1) {

                            FacilityTag(tag = "Special")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = state.textComplian.value,
                            onValueChange = { state.textComplian.value = it },
                            label = { Text("Put a reason") },
                            placeholder = { Text("Type here a Reason ") },
                            maxLines = 10, // Set the maximum number of lines
                            textStyle = TextStyle(color = Color.Black), // Set the text style
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.Black, // Set the text color
                                backgroundColor = Color.White, // Set the background color
                                cursorColor = Color(0xff15420F), // Set the cursor color
                                focusedBorderColor = Color(0xff15420F), // Set the focused border color
                                focusedLabelColor = Color.Black, // Set the focused label color
                                unfocusedBorderColor = Color(0xff15420F), // Set the unfocused border color
                                unfocusedLabelColor = Color.Black // Set the unfocused label color
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            viewModle.pushComplain()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff15420F),
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            text = "Send Your Complain", color = Color.White
                        )

                    }


                }


            }
        }

    }


}
