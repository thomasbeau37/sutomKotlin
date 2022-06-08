package com.mpwd2.momomotus.ui.pages.home

import com.mpwd2.momomotus.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    object Home: NavigationItem("home", R.drawable.account, "Home")
    object Leaderboard: NavigationItem("leaderboard", R.drawable.account, "Leaderboard")
    object Profile: NavigationItem("profile", R.drawable.account, "Profile")
    object Game: NavigationItem("game", R.drawable.account, "Game")
}