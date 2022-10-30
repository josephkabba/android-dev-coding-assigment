package com.ensibuuko.android_dev_coding_assigment.ui.screens.home

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ensibuuko.android_dev_coding_assigment.presentation.models.PostUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.HomeViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomButton
import com.ensibuuko.android_dev_coding_assigment.ui.composables.CustomTextField
import com.ensibuuko.android_dev_coding_assigment.ui.screens.home.components.PostCardView
import com.ensibuuko.android_dev_coding_assigment.ui.screens.login.LoginScreenStateHolder
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
private fun rememberHomeScreenState(
    resources: Resources = LocalContext.current.resources,
) = remember {
    HomeScreenStateHolder(resources)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    userId: Int,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val state = rememberHomeScreenState()

    val posts = viewModel.posts.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        sheetElevation = 6.dp,
        sheetGesturesEnabled = true,
        sheetShape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(10.dp),
            topEnd = CornerSize(10.dp),
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSecondary,
                title = {
                    Text(text = "Forum", color = customGreen)
                },

                actions = {
                    Box {
                        IconButton(onClick = { state.expand = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Top bar menu"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier.background(color = MaterialTheme.colors.background),
                            expanded = state.expand,
                            onDismissRequest = { state.expand = false }) {
                            DropdownMenuItem(onClick = {
                                scope.launch {
                                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    } else {
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                    }
                                }
                            }) {
                                Text(text = "Add post", color = Color.Black)
                            }
                        }
                    }
                },

                modifier = Modifier.padding(end = 5.dp)
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }

                    CustomTextField(
                        value = state.title,
                        isError = state.titleError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = "Title"
                    ) {
                        state.title = it
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )

                    CustomTextField(
                        value = state.body,
                        isError = state.bodyError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = "body"
                    ) {
                        state.body = it
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )

                    CustomButton(title = "Create") {
                        state.createPost { title, body ->
                            scope.launch {
                                val post = PostUIModel(
                                    body = body,
                                    title = title,
                                    userId = userId,
                                    id = System.currentTimeMillis().toInt(),
                                    createdAt = System.currentTimeMillis()
                                )
                                viewModel.createPost(post)
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                                state.title = ""
                                state.body = ""

                                scaffoldState.snackbarHostState.showSnackbar("Post Created")
                            }
                        }
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) { it ->
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            items(items = posts) {
                if (it != null) {
                    PostCardView(navController = navController, postUIModel = it, currentUserId = userId)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {

}