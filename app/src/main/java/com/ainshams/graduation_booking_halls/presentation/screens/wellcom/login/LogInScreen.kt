package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.navigation.graphs.Graph
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login.bio.Biometric
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LogInScreen(nav: NavHostController, activity: FragmentActivity) {
    val viewModle: LogInViewModle = hiltViewModel()
    val state = viewModle.state
    val email: MutableState<String> = remember {
        mutableStateOf("")
    }
    val password: MutableState<String> = remember {
        mutableStateOf("")
    }
//    val FingerState = remember {
//        mutableStateOf(false)
//    }
    val showBallon = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    BackHandler {
        if (nav.currentBackStackEntry?.destination?.route == WelcomeScreens.LogIn.route) {
            (context as? Activity)?.finish()
        } else {
            // Exit the app
        }
    }
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
//    sharedPreferences.edit()
//        .putBoolean("first_app_launch", true)
//        .apply()
//    val isFirstAppLaunch = sharedPreferences.getBoolean("first_app_launch", true)
//    sharedPreferences.edit().putBoolean("first_app_launch", false).apply()

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
            if (!viewModle.state.isLoading) {
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
                    Spacer(modifier = Modifier.size(100.dp))
                    Image(
                        painter = painterResource(id = R.drawable.splash_icon),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(40.dp))

                    OutlinedTextField(
                        value = email.value,
                        onValueChange = {
                            email.value = it
//                    onValueChange(it)
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
                    Spacer(modifier = Modifier.size(20.dp))

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
                    Spacer(modifier = Modifier.size(20.dp))
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "Do you want do it with Finger print?", color = Color.Black)
//                        Checkbox(
//                            checked = FingerState.value,
//                            onCheckedChange = { FingerState.value = it },
//                            colors = CheckboxDefaults.colors(Color(0xff15420F), Color.Black)
//                        )
//                    }
//                    Spacer(modifier = Modifier.size(20.dp))
//                    Balloon(
//                        builder = builder,
//                        balloonContent = {
//                            Text(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(150.dp),
//                                text = "you should Fill All Requirments",
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontSize = 15.sp
//                            )
//                        }
//                    ) { balloonWindow ->
//                        if (showBallon.value) {
//                            balloonWindow.showAlignBottom()
//
//                        }
//
//                        Button(
//                            onClick = {
//                                if (email.value == "" ||password.value=="" ){
//                                    showBallon.value= !showBallon.value
//                                }
//
//                                else{
//                                    val viewModelJob = Job()
//                                    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//
//                                    viewModle.state.emailField.value = email.value
//                                    viewModle.state.passwordField.value = password.value
//                                    CoroutineScope(Dispatchers.Main).launch {
//                                        val email = email.value
//                                        val password = password.value
//
//                                        viewModle.state.emailField.value = email
//                                        viewModle.state.passwordField.value = password
//
//                                        val authDeferred = viewModelScope.async { viewModle.getAuth() }
//                                        val authResult = authDeferred.await()
//                                        delay(2000L)
//                                        if (viewModle.state.isAuth){
//                                            Biometric.authenticate(
//                                                activity,
//                                                title = "Biometric Authentication",
//                                                subtitle = "Authenticate to proceed",
//                                                description = "Authentication is must",
//                                                negativeText = "Cancel",
//                                                onSuccess = {
//
//                                                    nav.navigate(Graph.HOME)
//                                                },
//                                                onError = { errorCode, errorString ->
//                                                    Toast.makeText(
//                                                        context,
//                                                        "Authentication error: $errorCode, $errorString",
//                                                        Toast.LENGTH_SHORT
//                                                    )
//                                                        .show()
//                                                },
//                                                onFailed = {
//                                                    Toast.makeText(
//                                                        context,
//                                                        "Authentication failed",
//                                                        Toast.LENGTH_SHORT
//                                                    )
//                                                        .show()
//                                                }
//                                            )
//                                        }
//
//                                    }
//                                }
//
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(start = 15.dp, end = 15.dp)
//                                .height(50.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                backgroundColor = Color(0xff15420F), // Set the background color
//                                contentColor = Color.White // Set the text color
//                            )
//                        ) {
//                            Text(text = "Log In"+ viewModle.auth.value)
//                        }
//
//
//                    }
                    Button(
                        onClick = {
//                            if (email.value == "" ||password.value=="" ){
//                                showBallon.value= !showBallon.value
//                            }
//
//                            else{
                                val viewModelJob = Job()
                                val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

                                viewModle.state.emailField.value = email.value
                                viewModle.state.passwordField.value = password.value
                                CoroutineScope(Dispatchers.Main).launch {
                                    val email = email.value
                                    val password = password.value

                                    viewModle.state.emailField.value = email
                                    viewModle.state.passwordField.value = password

                                    val authDeferred = viewModelScope.async { viewModle.getAuth() }
                                    val authResult = authDeferred.await()
                                    delay(2000L)
                                    if (viewModle.state.isAuth){
                                        Biometric.authenticate(
                                            activity,
                                            title = "Biometric Authentication",
                                            subtitle = "Authenticate to proceed",
                                            description = "Authentication is must",
                                            negativeText = "Cancel",
                                            onSuccess = {

                                                nav.navigate(Graph.HOME)
                                            },
                                            onError = { errorCode, errorString ->
                                                Toast.makeText(
                                                    context,
                                                    "Authentication error: $errorCode, $errorString",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            },
                                            onFailed = {
                                                Toast.makeText(
                                                    context,
                                                    "Authentication failed",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        )
                                    }

                                }
//                            }

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
                        Text(text = "Log In"+ viewModle.auth.value)
                    }


                    Spacer(modifier = Modifier.size(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                // First text - normal
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("Don't have an account? ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xff15420F),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                ) {
                                    append("Sign up")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .clickable { nav.navigate(WelcomeScreens.SignUp.route) },
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }
}