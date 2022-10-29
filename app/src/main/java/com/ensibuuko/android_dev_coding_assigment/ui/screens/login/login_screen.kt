package com.ensibuuko.android_dev_coding_assigment.ui.screens.login

import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.AccountViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomButton
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomTextField
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.launch

@Composable
private fun rememberLoginScreenState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    LoginScreenStateHolder(resources, navController)
}

@Composable
fun LoginScreen(navController: NavController, viewModel: AccountViewModel = hiltViewModel()) {
    val loginState = rememberLoginScreenState(navController = navController)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 28.sp)

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        CustomTextField(
            value = loginState.id,
            isError = loginState.idError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Enter your user ID"
        ) {
            loginState.id = it
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        if (loginState.showError or loginState.idError) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Card(
                backgroundColor = Color.Red,
                elevation = 20.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    color = Color.White, text = loginState.error, modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(title = "Login") {
                loginState.signInUser { id ->
                    scope.launch {
                        viewModel.getUser(id.toInt(), CachePolicy.Type.NEVER, true).collect {
                            when (it.status) {
                                Resource.Status.SUCCESS -> {
                                    viewModel.saveUserId(id.toInt())
                                }

                                Resource.Status.ERROR -> {

                                }

                                Resource.Status.LOADING -> {

                                }
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}