package com.example.internee_task2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun SignUpPage(navController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authStatus.observeAsState()

    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthStates.Authenticated -> navController.navigate("main_screen")
            is AuthStates.Error -> Toast.makeText(
                context,
                (authState.value as AuthStates.Error).message, Toast.LENGTH_LONG
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create an Account",
            style = TextStyle(
            fontSize = 33.sp,
            fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp, // Space between letters
                lineHeight = 32.sp //
            ),
                        modifier = Modifier
                            .padding(15.dp) // Padding around the text
                            .background(Color.LightGray) // Bac
        )
            Spacer(modifier = Modifier.height(25.dp))
        Image(
            painter = painterResource(id = R.drawable.internee_logo),
            contentDescription = null,
//            modifier = Modifier.size(100.dp),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(text = "Full name")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(text = "enter your email adress")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(text = "enter your password")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),

            )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                authViewModel.SignUpMethod(email, password)
            },
            enabled = authState.value != AuthStates.Loading,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(id = R.color.my_green),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "create account",
                fontSize = 20.sp
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(text = "Already have Account?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login",
                color = colorResource(id = R.color.my_green),
                modifier = Modifier.clickable {
                    navController.navigate("SignIn")
                }
            )

        }
        Spacer(modifier = Modifier.height(17.dp))

        Text(text = "or sign in with")

        Spacer(modifier = Modifier.height(15.dp))




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(painter = painterResource(id = R.drawable.fb),
                contentDescription = "facebook",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // facebook clicked
                    }
            )

            Image(painter = painterResource(id = R.drawable.google),
                contentDescription = "google",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // google clicked
                    }
            )

            Image(painter = painterResource(id = R.drawable.twitter),
                contentDescription = "Twitter",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Twitter clicked
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    SignUpPage(navController = navController, authViewModel =authViewModel )
}





