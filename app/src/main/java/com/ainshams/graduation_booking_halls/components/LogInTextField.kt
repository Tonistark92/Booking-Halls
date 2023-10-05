package com.ainshams.graduation_booking_halls.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogInTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxline: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {

    val keyBoardController =LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
           backgroundColor = Color.Transparent
        ),
        maxLines = maxline,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone ={ onImeAction()
           keyBoardController?.hide()
            }
        ),
        modifier = modifier.fillMaxWidth()

    )

}