package com.borabor.planetapp.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borabor.planetapp.ui.theme.PlanetAppTheme

@Composable
fun AuthorScreen(navController: NavController) {
}

@Preview(showBackground = true)
@Composable
fun AuthorScreenPreview() {
    PlanetAppTheme {
        AuthorScreen(navController = rememberNavController())
    }
}