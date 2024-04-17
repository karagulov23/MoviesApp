package com.orlo.moviesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orlo.moviesapp.presentation.MovieListViewModel
import com.orlo.moviesapp.presentation.screens.detail.DetailScreen
import com.orlo.moviesapp.ui.theme.MoviesAppTheme
import com.orlo.moviesapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
//                val movieListViewModel = hiltViewModel<MovieListViewModel>()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.rout
                ) {
                    composable(Screen.Home.rout) {
                        HomeScreen(navController)
                    }

                    composable(
                        Screen.Details.rout + "/{movieId}",
                        arguments = listOf(
                            navArgument("movieId") {type = NavType.IntType}
                        )
                    ) {
                        DetailScreen()
                    }
                }
            }
        }

    }


    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        LaunchedEffect(key1 = color) {
            systemUiController.setSystemBarsColor(color)
        }
    }

}
