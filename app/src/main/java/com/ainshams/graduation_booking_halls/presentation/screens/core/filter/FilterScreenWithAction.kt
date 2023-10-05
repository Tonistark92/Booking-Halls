package com.ainshams.graduation_booking_halls.presentation.screens.core.filter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.FilterDto
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.CustomDialog
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.FilterTag
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.TopFilterBar
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilterScreenWithAction(onItemClick: (id: Int, start: LocalTime, end: LocalTime, date: LocalDate) -> Unit) {
    val viewModle: FilterViewModel = hiltViewModel()
    val context = LocalContext.current
    val state = viewModle.state
    var showDialog by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
          MyTopBarBook()
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFECECEC)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { showDialog = !showDialog },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff15420F), // Set the background color
                        contentColor = Color.White // Set the text color
                    )
                ) {
                    Text(text = "Book  a Hall")
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .background(Color.Gray)
                        .padding(start = 15.dp, end = 15.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp), horizontalArrangement = Arrangement.Start) {
                    Text(text = "Results", color = Color.Black)

                }

                if (showDialog){
                    CustomDialog(onConfirm = {
                        showDialog = !showDialog
                    }, onDismiss = {
                        showDialog = !showDialog
                    })
                }

                if (state.isLoading) {
                    CircularProgressIndicator(color = Color(0xff15420F ), strokeWidth = 4.dp)
                } else if (state.networkError || state.notFound) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFECECEC)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "No Halls with those Credentials ")
                        Spacer(modifier = Modifier.height(200.dp))
                        LoaderForLotti1()
                    }
                } else if (state.halls.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFECECEC)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        LoaderForLotti2()
                    }

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFECECEC)),
                    ) {

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 50.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)

                        ) {
                            items(state.halls.size) { hallindex: Int ->
                                FilterItem(hall = state.halls[hallindex], viewModel = viewModle) {
                                    onItemClick(
                                        viewModle.state.halls[hallindex].hall_id,
                                        state.startTime,
                                        state.endTime,
                                        state.date
                                    )
                                }
                            }

                        }

                    }
                }
            }

        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun FilterItem(
    hall: FilterDto, viewModel: FilterViewModel, onItemClick: () -> Unit = {}
) {
    val painter = rememberImagePainter(

        data = if (hall.hall_photos .isEmpty()) {
            "https://booking-apis.larasci.site/image/hall_photos/1680268619.jpg"
        } else {
            "https://booking-apis.larasci.site/" + hall.hall_photos[0]
        },
        builder = {
//            placeholder(R.drawable.hall1)
            crossfade(1000)
            transformations(
                RoundedCornersTransformation(20f)
            )
        }
    )
    Box(
        modifier = Modifier
            .size(280.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
//        if (!viewModle.state.isLoading) {
//            CircularProgressIndicator(color = Color(0xff15420F ), strokeWidth = 4.dp)
//        }
        Column(
            Modifier
                .fillMaxSize()
                .clickable { onItemClick() }.padding(start = 4.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .width(350.dp)
                    .height(150.dp),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = hall.hall_name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Capacity : ")
                }

                // Second text - bold with 15.sp size
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(hall.capacity.toString())
                }
            })
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Type :")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(hall.type)
                }
            })
            Text(text = buildAnnotatedString {
                // First text - normal
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Location :  ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ) {
                    append(hall.floor_place.toString())
                }
            })

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun LoaderForLotti1() {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.data2))

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
 fun MyTopBarBook() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Booking",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}