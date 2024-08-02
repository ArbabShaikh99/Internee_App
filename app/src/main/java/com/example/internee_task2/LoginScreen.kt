package com.example.internee_task2

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignInPage(navController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }


    val authState = authViewModel.authStatus.observeAsState()

    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthStates.Authenticated -> navController.navigate("main_screen")
            is AuthStates.Error -> Toast.makeText(context,
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
        Image(
            painter = painterResource(id = R.drawable.internee_logo ),
            contentDescription =null,
//            modifier = Modifier.size(100.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            placeholder = {
                Text(text = "enter your email address")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),

            )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            placeholder = {
                Text(text = " Password")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),

            )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                authViewModel.SignInMethod(email,password)
            },
            enabled = authState.value != AuthStates.Loading,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(id = R.color.my_green),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Log in",
                fontSize = 20.sp
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(text = "Dont have Account?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Create new account",
                color = colorResource(id =R.color.my_green ),
                modifier = Modifier.clickable {
                    navController.navigate("SignUp")
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
fun SignInPagePreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    SignInPage(navController = navController, authViewModel =authViewModel )
}