package com.ensibuuko.android_dev_coding_assigment.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customBlue
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen

@Composable
fun CustomButton(modifier: Modifier = Modifier,
                 title: String,
                 enabled:Boolean = true,
                 color: Color = MaterialTheme.colors.primary,
                 onClick: () -> Unit){

    Button(onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.White,
            backgroundColor = color,
            contentColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        ),
        content = {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        })
}


@Composable
fun CustomTextField(modifier: Modifier = Modifier,
                    enabled: Boolean = true,
                    keyboardOptions: KeyboardOptions = KeyboardOptions(),
                    textFieldDefaults: TextFieldDefaults = TextFieldDefaults,
                    isError: Boolean = false,
                    visualTransformation: VisualTransformation = VisualTransformation.None,
                    value: String,
                    label: String,
                    trailingIcon: @Composable (() -> Unit) = {},
                    onValueChange: (String) -> Unit) {

    TextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        trailingIcon = trailingIcon,
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },

        colors = textFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = customGreen,
            focusedLabelColor = customBlue,
            errorLabelColor = Color.Red,
            errorCursorColor = Color.Red,
            errorIndicatorColor = Color.Red,
            errorTrailingIconColor = Color.Red,
            focusedIndicatorColor = customBlue
        ))
}