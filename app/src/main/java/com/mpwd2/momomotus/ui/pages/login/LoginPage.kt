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
fun LoginPage(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()


    LaunchedEffect(Unit) {
        viewModel.signUpState.collect {
            if (it is State.Success) {
                navController.navigate(NavigationKeys.Route.HOME)
            }
        }
    }

    var mEmailTextFieldValue by remember {
        mutableStateOf(TextFieldValue("loginsoso@gmail.com"))
    }
    var mPasswordTextFieldValue by remember {
        mutableStateOf(TextFieldValue("sfezaeza"))
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Se connecter")})
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
                viewModel.login(
                    mEmailTextFieldValue.text,
                    mPasswordTextFieldValue.text,
                )
            }) {
                Text(text = "Enregistrer")
            }
        }
    }


}