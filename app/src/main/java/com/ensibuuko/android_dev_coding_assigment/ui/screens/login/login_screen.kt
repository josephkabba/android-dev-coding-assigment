package com.ensibuuko.android_dev_coding_assigment.ui.screens.login

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ensibuuko.android_dev_coding_assigment.R
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomButton
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomTextField

@Composable
private fun rememberLoginScreenState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    LoginScreenStateHolder(resources, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    val loginState = rememberLoginScreenState(navController = navController)

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(25.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 28.sp)

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        CustomTextField(value = loginState.email,
            isError = loginState.emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Email") {
            loginState.email = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        CustomTextField(value = loginState.password,
            isError = loginState.passwordError,
            trailingIcon = {
                if (loginState.showPassword){
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_visibility_off_24), contentDescription = "see password")
                    }, onClick = {
                        loginState.showPassword = false
                    })
                }else {
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_eye_24), contentDescription = "eye")
                    }, onClick = {
                        loginState.showPassword = true
                    })
                }
            },
            visualTransformation = if (loginState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = "Password") {
            loginState.password = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        if (loginState.showError or loginState.passwordError or loginState.emailError){
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp))

            Card (colors = CardDefaults.cardColors(containerColor = Color.Red), elevation = CardDefaults.cardElevation(defaultElevation = 20.dp), shape = MaterialTheme.shapes.medium)  {
                Text(color = Color.White, text = loginState.error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp))
            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(title = "Login") {
                loginState.signInUser { email, password ->
                   
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(23.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(navController = rememberNavController())
}