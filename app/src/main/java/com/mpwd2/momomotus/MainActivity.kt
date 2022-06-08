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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mpwd2.momomotus.ui.pages.game.GamePage
import com.mpwd2.momomotus.ui.pages.home.HomeScreen
import com.mpwd2.momomotus.ui.pages.home.LeaderboardScreen
import com.mpwd2.momomotus.ui.pages.home.NavigationItem
import com.mpwd2.momomotus.ui.pages.home.ProfileScreen
import com.mpwd2.momomotus.ui.theme.MomomotusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Leaderboard,
        NavigationItem.Game
    )

    @Composable
    fun Navigation(navController: NavHostController){
        NavHost(navController, startDestination = NavigationItem.Home.route){
            composable(NavigationItem.Home.route){
                HomeScreen();
            }
            composable(NavigationItem.Profile.route){
                ProfileScreen();
            }
            composable(NavigationItem.Leaderboard.route){
                LeaderboardScreen();
            }
            composable(NavigationItem.Game.route){
                GamePage();
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController();
            MomomotusTheme {
                Scaffold (
                    topBar = {
                        TopAppBar(
                            title= {(Text(text = "Coucou"))}
                        )
                    },
                    bottomBar = {
                        BottomNavigation(backgroundColor = Color.Red) {
                            val navBackStackEntry = navController.currentBackStackEntryAsState()
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
                                              navController.navigate(item.route){
                                                  navController.graph.startDestinationRoute?.let{route ->
                                                      popUpTo(route){
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

                ){
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MomomotusTheme {
        Greeting("Android")
    }
}