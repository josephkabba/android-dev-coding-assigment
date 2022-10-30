package com.ensibuuko.android_dev_coding_assigment.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.HomeViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.screens.home.components.PostCardView
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val posts = viewModel.posts.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSecondary,
                title = {
                    Text(text = "Forum", color = customGreen)
                },

                modifier = Modifier.padding(end = 5.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(50),
                backgroundColor = customGreen,
                contentColor = Color.White,
                onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "create new post")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false
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
                    PostCardView(navController = navController, postUIModel = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}