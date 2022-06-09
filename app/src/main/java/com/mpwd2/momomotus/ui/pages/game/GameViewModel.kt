package com.mpwd2.momomotus.ui.pages.game

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.data.entities.Word
import com.mpwd2.momomotus.data.repositories.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val repository: WordRepository): ViewModel(){

    init{
        getWordOfTheDay()
    }

    private lateinit var motAtrouve : String
    private val mState = MutableStateFlow<State<Word>>(State.loading())
    val state: StateFlow<State<Word>>
        get() = mState

    var currentWord = ""

    fun checkWin(){
        if(mState.value is State.Success ){
            if(motAtrouve.length == currentWord.length){
                if(motAtrouve == currentWord){
                    println("win")
                }else{
                    println("nextrow")
                }
            }

        }
    }

    private fun getWordOfTheDay(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                repository.getRandomWord().collect(){
                    motAtrouve = it.name
                    mState.value = State.success(it)
                }
            }catch(ex: Exception){
                mState.value = State.failed(ex.localizedMessage)
            }
        }
    }
}