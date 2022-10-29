package com.ensibuuko.android_dev_coding_assigment.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector? = null, val name: String = "") {
    object HomeScreen: Screen(route = "home_screen", icon = Icons.Default.Home, name = "Home")
    object AccountScreen: Screen(route = "account_screen", icon = Icons.Default.AccountCircle, name = "Account")
    object LoginScreen: Screen(route = "login_screen", icon = null, name = "Login")
    object PostScreen: Screen(route = "post_screen", icon = null, name = "Post")
}
