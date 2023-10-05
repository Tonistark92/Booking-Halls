package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.SignUp

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.components.ShowSnackbar
import com.ainshams.graduation_booking_halls.data.remote.dto.CourseDtoItem
import com.ainshams.graduation_booking_halls.navigation.graphs.Graph
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login.bio.Biometric
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
import java.time.LocalDate


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(nav: NavHostController) {
    val viewModle: SignUpViewModle = hiltViewModel()
    val state = viewModle.state
    val context = LocalContext.current
    val email: MutableState<String> = remember {
        mutableStateOf("")
    }
    val password: MutableState<String> = remember {
        mutableStateOf("")
    }
    val name: MutableState<String> = remember {
        mutableStateOf("")
    }
    val phone: MutableState<String> = remember {
        mutableStateOf("")
    }
    val specialization: MutableState<String> = remember {
        mutableStateOf("")
    }
    val showBallon = remember {
        mutableStateOf(false)
    }
    Scaffold(
        snackbarHost = {
//           ShowSnackbar(viewModle.snackbarHostState, if(state.isAuth){"Authantic wellcom !"}else{"sorry you are not Authantic"})
            SnackbarHost(viewModle.snackbarHostState) { snackbarData ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                    }
                ) {
                    Text(text = snackbarData.message)
                }
            }

        }
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (viewModle.state.isLoading) {
                CircularProgressIndicator(color = Color(0xff15420F), strokeWidth = 4.dp)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffF6F6F6)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.splash_icon),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    OutlinedTextField(
                        value = name.value,
                        onValueChange = {
                            name.value = it
                        },
                        label = { Text(text = "Enter Name", color = Color(0xff15420F)) },
                        singleLine = true,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black, // Change the text color
                            backgroundColor = Color.White, // Change the background color
                            cursorColor = Color(0xff15420F), // Change the cursor color
                            focusedIndicatorColor = Color(0xff15420F), // Change the focused indicator color
                            unfocusedIndicatorColor = Color.Black, // Change the unfocused indicator color
                        )

                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = {
                            email.value = it
                        },
                        label = { Text(text = "Enter Email", color = Color(0xff15420F)) },
                        singleLine = true,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black, // Change the text color
                            backgroundColor = Color.White, // Change the background color
                            cursorColor = Color(0xff15420F), // Change the cursor color
                            focusedIndicatorColor = Color(0xff15420F), // Change the focused indicator color
                            unfocusedIndicatorColor = Color.Black, // Change the unfocused indicator color
                        )

                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {
                            password.value = it
//                    onValueChange(it)
                        },
                        label = { Text(text = "Password", color = Color(0xff15420F)) },
                        singleLine = true,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black, // Change the text color
                            backgroundColor = Color.White, // Change the background color
                            cursorColor = Color(0xff15420F), // Change the cursor color
                            focusedIndicatorColor = Color(0xff15420F), // Change the focused indicator color
                            unfocusedIndicatorColor = Color.Black, // Change the unfocused indicator color
                        ),
                        visualTransformation = PasswordVisualTransformation()

                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    OutlinedTextField(
                        value = phone.value,
                        onValueChange = {
                            phone.value = it
                        },
                        label = {
                            Text(
                                text = "Enter Your Phone Number",
                                color = Color(0xff15420F)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black, // Change the text color
                            backgroundColor = Color.White, // Change the background color
                            cursorColor = Color(0xff15420F), // Change the cursor color
                            focusedIndicatorColor = Color(0xff15420F), // Change the focused indicator color
                            unfocusedIndicatorColor = Color.Black, // Change the unfocused indicator color
                        )

                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    val l = listOf<String>(
                        "CS",
                        "Math",
                        "Physics",
                        "Statistics",
                        "Biology",
                        "Chemistry"
                    )
                    dropDownMenu(
                        list = l
                    ) {
                        specialization.value = it
                    }
                    Spacer(modifier = Modifier.size(20.dp))
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
                        setBalloonHighlightAnimation(BalloonHighlightAnimation.HEARTBEAT)
                        setOverlayShape(
                            BalloonOverlayRoundRect(
                                R.dimen.tool_tip,
                                R.dimen.tool_tip
                            )
                        )
                        setDismissWhenClicked(true)
                    }
                    Balloon(
                        builder = builder,
                        balloonContent = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                text = "you should Fill All Requirments",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    ) { balloonWindow ->
                        if (showBallon.value) {
                            balloonWindow.showAlignBottom()

                        }

                        Button(
                            onClick = {
                                if (email.value == "" || password.value == "" ||
                                    email.value == "" || phone.value == ""||specialization.value=="") {
                                    showBallon.value =!showBallon.value

                                }
                                else{
                                    val viewModelJob = Job()
                                    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
                                    viewModle.state.name.value = email.value
                                    viewModle.state.password.value = password.value
                                    viewModle.state.email.value = email.value
                                    viewModle.state.phone.value = phone.value
                                    viewModle.state.specialization.value = specialization.value
                                    state.isLoading = true
                                    CoroutineScope(Dispatchers.Main).launch {
                                        val h = viewModelScope.async { viewModle.pushSignUp() }
                                        h.onAwait
                                        delay(2000L)
                                        nav.navigate(WelcomeScreens.LogIn.route)

                                    }
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff15420F), // Set the background color
                                contentColor = Color.White // Set the text color
                            )
                        ) {
                            Text(text = "Sign Up")
                        }


                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                // First text - normal
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("Do have an account? ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xff15420F),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    ),


                                    ) {
                                    append("Log In Now !")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .clickable {
                                    nav.navigate(WelcomeScreens.LogIn.route)
                                },
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }


}

@Composable
fun dropDownMenu(
    list: List<String>?,
    onSelected: (course: String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = list
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { enterdTest ->
                selectedText = enterdTest
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Choose Your Specialization") },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded }, tint = Color(0xff15420F)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Set text color
                backgroundColor = Color.White, // Set background color
                focusedLabelColor = Color.LightGray, // Set focused hint color
                unfocusedLabelColor = Color.LightGray, // Set unfocused hint color
                disabledLabelColor = Color.LightGray, // Set disabled hint color
                cursorColor = Color.Transparent, // Hide the cursor
                focusedBorderColor = Color.Transparent, // Remove border when focused
                unfocusedBorderColor = Color.Transparent, // Remove border when unfocused
                disabledBorderColor = Color.Transparent,// Remove border when disabled
                leadingIconColor = Color(0xff15420F)
            ),
            enabled = false

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .background(Color.White)
        ) {
            suggestions?.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    onSelected(label)
                    expanded = false
                }) {
                    Text(text = label, color = Color.Black)
                }
            }
        }
    }

}