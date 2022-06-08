package com.mpwd2.momomotus.ui.pages.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpwd2.momomotus.data.entities.State
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*


@Composable
fun GamePage(){
    val viewModel: GameViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value

    if(state is State.Success){
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                ) {
            WordRow(
                nbRow = 6, word = state.data.name
            )
        }
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center)
//        ) {
//            Text(text = state.data.name)
//        }
    }else if(state is State.Loading){
        CircularProgressIndicator()
    }else{

    }
}

@Composable
fun LetterRow(letter: String, modifier : Modifier){
    var text by remember { mutableStateOf(TextFieldValue(".")) }

    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {newText -> text = newText },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Red
        )
    )
}

@Composable
fun WordRow(nbRow: Int, word: String){
    for(i in 1..nbRow){
        Row(
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .border(1.dp, Color.White)
        ) {
            word.toCharArray().forEach {
                LetterRow(letter = it.toString(),
                    modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f))
            }
        }
    }
}
