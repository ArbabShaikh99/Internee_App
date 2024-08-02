package com.example.internee_task2


import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.internee_task2.ui.theme.Internee_Task2Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
      //   FirebaseApp.initializeApp(this)

        enableEdgeToEdge()


        val authViewModel:AuthViewModel by viewModels()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Internee_Task2Theme {

                    Surface ( modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background){

                        MainNavigation(authViewModel=authViewModel)
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color=color)
    }
}








