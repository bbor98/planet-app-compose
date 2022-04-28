package com.borabor.planetapp.util

sealed class Screen(val route:String) {
    object Home:Screen(route = "home_screen")
    object Author:Screen(route = "author_screen")
}