package com.ainshams.graduation_booking_halls.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String,
    actionLabel: String? = null,
    action: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(snackbarHostState) {
        snackbarHostState.showSnackbar(message)
    }

    SnackbarHost(snackbarHostState) { snackbarData ->
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                if (actionLabel != null && action != null) {
                    TextButton(
                        onClick = {
                            action.invoke()
                            snackbarHostState.currentSnackbarData?.dismiss()
                        }
                    ) {
                        Text(text = actionLabel)
                    }
                }
            }
        ) {
            Text(text = snackbarData.message)
        }
    }
}