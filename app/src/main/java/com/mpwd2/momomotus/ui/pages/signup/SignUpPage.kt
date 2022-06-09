package com.mpwd2.momomotus.ui.pages.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.data.entities.User
import com.mpwd2.momomotus.ui.navigation.NavigationKeys

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpPage(navController: NavController) {
    val viewModel: SignUpViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.signUpState.collect {
            if (it is State.Success) {
                navController.navigate(NavigationKeys.Route.HOME)
            }
        }
    }

    var mEmailTextFieldValue by remember {
        mutableStateOf(TextFieldValue("test@gmail.com"))
    }
    var mPasswordTextFieldValue by remember {
        mutableStateOf(TextFieldValue("sfezaeza"))
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "S'inscrire")})
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(value = mEmailTextFieldValue , onValueChange = { mEmailTextFieldValue = it})
            Box(modifier = Modifier.height(16.dp))
            TextField(value = mPasswordTextFieldValue, onValueChange = {mPasswordTextFieldValue = it})
            Box(modifier = Modifier.height(16.dp))
            TextButton(onClick = {
                viewModel.signUp(
                    mEmailTextFieldValue.text,
                    mPasswordTextFieldValue.text,
                    User(
                        email = mEmailTextFieldValue.text,
                        id = "5525",
                        score = 200,
                        pseudo = "askayo",
                        image = "https://upload.wikimedia.org/wikipedia/commons/5/57/Chicken_-_melbourne_show_2005.jpg"

                    )
                )
            }) {
                Text(text = "Enregistrer")
            }
            TextButton(onClick = {
                navController.navigate(NavigationKeys.Route.LOGIN)
            }) {
                Text(text = "Login page")
            }
        }
    }


}