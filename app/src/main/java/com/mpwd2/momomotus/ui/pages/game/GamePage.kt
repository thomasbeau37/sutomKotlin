package com.mpwd2.momomotus.ui.pages.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.mpwd2.momomotus.data.entities.Letter
import com.mpwd2.momomotus.ui.pages.home.NavigationItem


@Composable
fun GamePage(){
    val viewModel: GameViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value

    //if(state is State.Success){
    if(1 == 1){
        Text(text = "poule")//state.data.name)
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                ) {
            WordRow(
                vm = viewModel,
                nbRow = 6,  word = "poule"//state.data.name
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

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LetterRow(row: Int, lett: String, enabled: Boolean,vm: GameViewModel, letter: String,index: Int, modifier : Modifier, onValidating: (String) -> Unit){
    var char = if(index == 0)letter else lett
    var text by remember { mutableStateOf(TextFieldValue(char)) }
    var fm = LocalFocusManager.current
    //val enabled = if(index == 0)false else true
    val readonly = if(index == 0)true else false
    val maxChar = 1
    var vide : Boolean by remember { mutableStateOf(true) }

    val context = LocalContext.current

    if(index == 0) onValidating(letter)
    Box(
        modifier = modifier,
    ) {
        Box(
        ) {

        }
        TextField(
            modifier = Modifier.onKeyEvent {
                if(it.key == Key.Backspace){
                   fm.moveFocus(FocusDirection.Previous)
               }
                true
            },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            singleLine = true,
            value = text,
            placeholder = {Text(text = ".", color = Color.White)},
            enabled = enabled,
            readOnly = readonly,
            onValueChange = {
                onValidating.invoke(it.text)
                if(it.text.length == maxChar && index != 0)
                {
                    text = it
                    fm.moveFocus(FocusDirection.Next)
                }else if(it.text.isEmpty()){
                    vide = true
                    text = it
                    vm.currentWord += it
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.White
            ),
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WordRow(nbRow: Int, word: String, vm: GameViewModel){
    val context = LocalContext.current
    for(i in 1..nbRow){
        Row(
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                //.border(1.dp, Color.White)
        ) {
            word.toCharArray().forEachIndexed {index, it ->
                var color = Color.Blue
                var enabled = true
                if(index == 0){
                    enabled = false
                }
                var lett = ""
                val letterTab = vm.stateTest.collectAsState().value
                if(letterTab is State.Success) {
                    //println("i:"+(i-1).toString()+" index:"+index.toString())
                    val letter = letterTab.data[i-1][index]
                    color = letter.color
                    enabled = letter.enabled
                    lett = letter.letter
                }
                else{
                    color = Color.Blue
                }
                LetterRow(
                    row = i,
                    lett = lett,
                    enabled = enabled,
                    vm = vm,
                    letter = it.toString(),
                    index = index,
                    modifier = Modifier
                        .background(color)
                        .weight(1f)
                        .aspectRatio(1f)
                        .fillMaxHeight()
                        .border(1.dp, Color.Black)){

                        if(index >= vm.currentWord.length){
                            vm.currentWord += it
                            println(vm.currentWord)
                        }else if(index == 0){
                            vm.currentWord = vm.motAtrouve.first().toString()
                        }

                        vm.checkWin(i-1, context)

                    }
                }
            }
        }
    }


fun LetterInput(string: String){
    println(string)
}