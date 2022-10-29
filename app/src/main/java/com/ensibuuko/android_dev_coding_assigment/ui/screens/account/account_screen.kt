package com.ensibuuko.android_dev_coding_assigment.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.AccountViewModel


@Composable
fun AccountScreen(navController: NavController, viewModel: AccountViewModel = hiltViewModel()) {

}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(navController = rememberNavController())
}