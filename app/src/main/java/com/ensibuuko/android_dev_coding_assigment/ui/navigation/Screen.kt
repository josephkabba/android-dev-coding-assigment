package com.ensibuuko.android_dev_coding_assigment.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val deepLink: String = "", val icon: ImageVector? = null, val name: String = "") {
    object HomeScreen: Screen(route = "home_screen", deepLink = "", icon = Icons.Default.Home, name = "Home")
    object AccountScreen: Screen(route = "account_screen", deepLink = "", icon = Icons.Default.AccountCircle, name = "Account")
    object LoginScreen: Screen(route = "login_screen", deepLink = "", icon = null, name = "Login")
    object PostScreen: Screen(route = "post_screen", deepLink = "post_screen/{id}", icon = null, name = "Post")
    object ProfileScreen: Screen(route = "profile_screen", deepLink = "profile_screen/{id}", icon = null, name = "Profile")
}
