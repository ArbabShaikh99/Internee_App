package com.example.internee_task2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("SignIn") {
            SignInPage(navController,authViewModel)
        }
        composable("SignUp"){
            SignUpPage(navController = navController, authViewModel = authViewModel)
        }
        composable("main_screen"){
            MainScreen(navController = navController, authViewModel = authViewModel)
        }
        // Add more composable destinations as needed
    }
}