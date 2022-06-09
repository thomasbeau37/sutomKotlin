package com.mpwd2.momomotus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mpwd2.momomotus.ui.navigation.NavigationKeys
import com.mpwd2.momomotus.ui.pages.signup.LoginPage
import com.mpwd2.momomotus.ui.pages.signup.SignUpPage
import com.mpwd2.momomotus.ui.theme.MomomotusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomomotusTheme {
                NavigationApp()
            }
        }
    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.SIGN_UP ){
        composable(route = NavigationKeys.Route.SIGN_UP){
            SignUpPage(navController = navController)
        }
        composable(route = NavigationKeys.Route.HOME){
            HomePage(navController = navController)
        }
        composable(route = NavigationKeys.Route.LOGIN){
            LoginPage(navController = navController)
        }
    }
}


