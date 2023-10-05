package com.ainshams.graduation_booking_halls.presentation.screens.core.filter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.FilterDto
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.CustomDialog
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.FilterTag
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components.TopFilterBar
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import java.time.LocalDate
import java.time.LocalTime


//private inline fun log(msg: () -> String) = debugLog("filter_screen", msg)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilterScreen(nav: NavController) {

    Scaffold(
        topBar = { MyTopBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFECECEC)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.filter_img),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(text = "Let's Have", color = Color.Black, fontSize = 25.sp)
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 25.sp
                    )
                ) {
                    append("an Amazing ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color =Color(0xff15420F),
                        fontSize = 25.sp
                    )
                ) {
                    append("Hall")
                }
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { nav.navigate("filter") },
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
        }
    }

}

@Composable
fun MyTopBar() {
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
