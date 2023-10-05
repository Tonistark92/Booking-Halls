package com.ainshams.graduation_booking_halls.presentation.screens.core.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.components.InputField
import com.ainshams.graduation_booking_halls.data.remote.dto.HallsDtoItem
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.launch

@Composable
fun Home(nav: NavHostController, onItemClick: (id: Int) -> Unit) {

    // composer ->
    val viewModle: HomeViewModle = hiltViewModel()
    val counter  = remember {
        mutableStateOf(0)

    }

    val state = viewModle.state
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
    val lifecycleOwner = LocalLifecycleOwner.current
    // column  row  box
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF6F6F6)), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current

        BackHandler {
            if (nav.currentBackStackEntry?.destination?.route == HallsScreans.HomeScrean.name) {
                (context as? Activity)?.finish()
            } else {
                // Exit the app
//                exitApp(context,lifecycleOwner )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "",
//                modifier = Modifier
//
//            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(25.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Booking Hall",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
        }
        InputField(
            valueState = state.searchQuery,
            labelId = "Enter Hall Name",
            enabled = true,
            isSingleLine = true,
            onAction = KeyboardActions {

            },
            onValueChange = {
                viewModle.onEvent(
                    HomeEvent.OnSearchQueryChange(it)
                )
            }
        )
        if (state.notFound == true) {
            LoaderForLotti1()
        } else if (state.networkError == true) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Check Your Internet",
                    color = Color.Red,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                )
            }

        }
        else if (viewModle.state.halls.size == 0){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Check Your Internet",
                    color = Color.Red,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                )
            }
        }
        else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 55.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                items(viewModle.state.halls.size) { indexHall ->
                    HallItem(viewModle.state.halls[indexHall], viewModle) {
                        onItemClick(viewModle.state.halls[indexHall].hall_id)
                    }
                }
            }
        }
    }
}

@Composable
fun HallItem(
    hall: HallsDtoItem,
    viewModle: HomeViewModle,
    onItemClick: () -> Unit = {}
) {
    val painter = rememberImagePainter(

        data = if (hall.photos.isEmpty()) {
            "https://booking-apis.larasci.site/image/hall_photos/1680268619.jpg"
        } else {
            "https://booking-apis.larasci.site/" + hall.photos[0]
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
                .clickable { onItemClick() }
                .padding(start = 4.dp)
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
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.data))

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

fun exitApp(context: Context, lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            // Perform any necessary cleanup or saving operations here

            // Exit the app
            System.exit(0)
        }
    }
}