package com.ensibuuko.android_dev_coding_assigment.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.AccountViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.navigation.Screen
import com.ensibuuko.android_dev_coding_assigment.ui.screens.account.AccountScreen
import com.ensibuuko.android_dev_coding_assigment.ui.screens.home.HomeScreen
import com.ensibuuko.android_dev_coding_assigment.ui.screens.login.LoginScreen
import com.ensibuuko.android_dev_coding_assigment.ui.screens.post.PostScreen
import com.ensibuuko.android_dev_coding_assigment.ui.screens.profile.ProfileScreen

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accountViewModel: AccountViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        setContent {
            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val scaffoldState: ScaffoldState = rememberScaffoldState()
                    var idExists by remember { mutableStateOf(false) }
                    val navController = rememberNavController()
                    var id by remember {
                        mutableStateOf(0)
                    }

                    LaunchedEffect(scaffoldState) {
                        accountViewModel.currentUserId.collect {
                            if (it != null) {
                                idExists = true
                                id = it
                            }
                        }
                    }

                    if (idExists) {
                        MainApp(navController, id)
                    } else {
                        LoginScreen(navController = navController)
                    }
                }
            }

        }
    }
}

@Composable
fun MainApp(navController: NavHostController, id: Int) {

    val screens = listOf(
        Screen.HomeScreen,
        Screen.AccountScreen
    )

    Scaffold(

        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black,
                elevation = 6.dp,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->

                    BottomNavigationItem(
                        icon = {
                            screen.icon?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = screen.route
                                )
                            }
                        },
                        label = { Text(text = screen.name, color = Color.Black) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->


        NavHost(
            navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController, userId = id)
            }
            composable(Screen.AccountScreen.route) {
                AccountScreen(navController = navController, id)
            }
            composable(
                Screen.ProfileScreen.deepLink,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                it.arguments?.let { it1 ->
                    ProfileScreen(
                        navController = navController,
                        it1.getInt("id")
                    )
                }
            }
            composable(
                Screen.PostScreen.deepLink,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                it.arguments?.let { it1 ->
                    PostScreen(
                        navController = navController,
                        userId = id,
                        postId = it1.getInt("id")
                    )
                }
            }

            composable(
                Screen.LoginScreen.route
            ){
                LoginScreen(navController = navController)
            }

        }
    }
}