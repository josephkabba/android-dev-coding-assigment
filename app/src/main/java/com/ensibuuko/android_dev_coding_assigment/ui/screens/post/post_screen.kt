package com.ensibuuko.android_dev_coding_assigment.ui.screens.post

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.PostViewModel

@Composable
fun PostScreen(navController: NavController, postId: Int, viewModel: PostViewModel = hiltViewModel()) {

}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    PostScreen(navController = rememberNavController(), 0)
}