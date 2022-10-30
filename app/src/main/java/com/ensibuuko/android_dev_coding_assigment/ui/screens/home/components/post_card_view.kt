package com.ensibuuko.android_dev_coding_assigment.ui.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ensibuuko.android_dev_coding_assigment.presentation.models.PostUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.UserUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.HomeViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.navigation.Screen
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun PostCardView(
    navController: NavController,
    postUIModel: PostUIModel,
    currentUserId: Int,
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    var user: UserUIModel? by remember {
        mutableStateOf(null)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(scaffoldState) {
        viewModel.getUser(postUIModel.userId, cachePolicy = CachePolicy.Type.REFRESH).collect {
            if (it?.status == Resource.Status.SUCCESS) {
                user = it.data
            }
        }
    }

    Box(modifier = Modifier.padding(horizontal = 10.dp)) {

        if (showDialog) {
            AlertDialog(onDismissRequest = {
                showDialog = false
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                shape = RoundedCornerShape(10.dp),
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        scope.launch {
                            viewModel.deletePost(postUIModel)
                        }
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                         TextButton(onClick = {
                             showDialog = false
                         }) {
                             Text(text = "Dismiss")
                         }
                },
                title = {
                        Text(text = "Delete Post")
                },
                text = {
                       Text("Do you want to delete the post")
                },
                properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ))
        }

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (user != null) {
                    navController.navigate(Screen.PostScreen.route + "/" + "${postUIModel.id}")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .width(306.dp)
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colors.background
                    )
                    .padding(10.dp)
            ) {
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    color = customGreen,
                    text = postUIModel.title.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Normal,
                    text = postUIModel.body.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })

                Spacer(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    if (user != null) {
                        ClickableText(
                            text = AnnotatedString("by ${user!!.name}"),
                            style = TextStyle(fontStyle = FontStyle(20), fontWeight = FontWeight.Light),
                            onClick = {
                                if (user != null) {
                                    navController.navigate("${Screen.ProfileScreen.route}/${user!!.id}")
                                }
                            })

                        if (user!!.id == currentUserId){
                            IconButton(onClick = { showDialog = !showDialog }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                            }
                        }
                    }
                }

            }
        }
    }
}
