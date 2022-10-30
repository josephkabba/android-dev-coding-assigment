package com.ensibuuko.android_dev_coding_assigment.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ensibuuko.android_dev_coding_assigment.presentation.models.UserUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.AccountViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customBlue
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGray
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource


@Composable
fun ProfileScreen(
    navController: NavController,
    id: Int,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AccountViewModel = hiltViewModel()
) {
    var user: UserUIModel? by remember {
        mutableStateOf(null)
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var error by remember {
        mutableStateOf("")
    }

    LaunchedEffect(scaffoldState) {
        error = ""
        viewModel.getUser(id, cachePolicy = CachePolicy.Type.REFRESH).collect {
            if (it?.status == Resource.Status.SUCCESS) {
                user = it.data


            } else if (it?.status == Resource.Status.ERROR) {
                error = it.message!!
            }

        }
        loading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSecondary,
                title = {
                    if (user != null) {
                        Text(text = user!!.name, color = customGreen)
                    }
                },

                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }

                },

                modifier = Modifier.padding(end = 5.dp)
            )
        }
    ) {

        if (!loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                Text(text = "Personal Details", fontSize = 20.sp, color = customBlue)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(customGray)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        if (user != null) {
                            Text(
                                text = "Name: ${user!!.name}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Phone: ${user!!.phone}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Website: ${user!!.website}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Email: ${user!!.email}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )



                Text(text = "Address Details", fontSize = 20.sp, color = customBlue)


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(customGray)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        if (user != null) {
                            Text(
                                text = "City: ${user!!.address.city}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Street: ${user!!.address.street}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Suite: ${user!!.address.suite}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Zipcode: ${user!!.address.zipcode}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }

                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                Text(text = "Company Details", fontSize = 20.sp, color = customBlue)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(customGray)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        if (user != null) {
                            Text(
                                text = "Name: ${user!!.company.name}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Catch Phrase: ${user!!.company.catchPhrase}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "B-S: ${user!!.company.bs}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}