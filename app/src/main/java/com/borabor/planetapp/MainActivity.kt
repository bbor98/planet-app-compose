package com.borabor.planetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.borabor.planetapp.screen.AuthorScreen
import com.borabor.planetapp.screen.HomeScreen
import com.borabor.planetapp.ui.theme.PlanetAppTheme
import com.borabor.planetapp.util.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetAppTheme {
                MaterialTheme(darkColors()) {
                    Surface {
                        val navController = rememberNavController()
                        SetupNavController(navHostController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SetupNavController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navHostController) }
        composable(Screen.Author.route) { AuthorScreen(navHostController) }
    }
}