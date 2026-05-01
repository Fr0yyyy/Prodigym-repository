package com.example.prodigym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prodigym.ui.exercise.ExerciseScreen
import com.example.prodigym.ui.profile.ProfileScreen
import com.example.prodigym.ui.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.LOGIN) {
            // TODO: LoginScreen
        }
        composable(Routes.HOME) {
            ExerciseScreen()
        }
        composable(Routes.PROFILE) {
            ProfileScreen()
        }
    }
}
