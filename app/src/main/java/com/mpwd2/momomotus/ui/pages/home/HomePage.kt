package com.mpwd2.momomotus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.momomotus.ui.ui.pages.profile.Profile
import com.mpwd2.momomotus.ui.pages.game.GamePage
import com.mpwd2.momomotus.ui.pages.home.*
import com.mpwd2.momomotus.ui.pages.leaderboard.LeaderboardScreen
import com.mpwd2.momomotus.ui.pages.signup.LoginViewModel
import com.mpwd2.momomotus.ui.theme.MomomotusTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()

    viewModel.getUser()
    val bottomNavigationController = rememberNavController()
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Leaderboard,
        NavigationItem.Game
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { (Text(text = "Coucou "+ viewModel.getUser().pseudo)) }
            )
        },
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Red) {
                val navBackStackEntry = bottomNavigationController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.route,
                        selectedContentColor = Color.Yellow,
                        unselectedContentColor = Color.Black,
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        onClick = {
                            bottomNavigationController.navigate(item.route) {
                                bottomNavigationController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }

    ) {
        Navigation(navController = bottomNavigationController)
    }
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen();
        }
        composable(NavigationItem.Profile.route) {
            Profile()
        }
        composable(NavigationItem.Leaderboard.route) {
            LeaderboardScreen();
        }
        composable(NavigationItem.Game.route) {
            GamePage();
        }
    }
}
