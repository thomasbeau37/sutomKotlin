package com.mpwd2.momomotus.ui.pages.game

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpwd2.momomotus.data.entities.Letter
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


    var mStateTest = MutableStateFlow<State<MutableList<MutableList<Letter>>>>(State.loading())
    val stateTest: StateFlow<State<MutableList<MutableList<Letter>>>>
        get() = mStateTest

    var letterTab: MutableList<MutableList<Letter>> = mutableListOf()

    fun initList(){
        for(y in 1 until 5){
            var listLetter : MutableList<Letter> = mutableListOf()
            for(i in 1 until motAtrouve.toList().size){
                listLetter.add(Letter(Color.Blue, true))
            }
            letterTab.add(listLetter)
        }
    }

    fun checkWin(){
        if(mState.value is State.Success ){
            if(motAtrouve.length == currentWord.length){
                if(motAtrouve == currentWord){
                    println("win")
                }else{
                    checkLetter()
                    println("nextrow")
                    letterTab[0][1] = Letter(Color.Red, false)
                    mStateTest.value = State.success(letterTab)
                    currentWord = ""
                }
            }

        }
    }

    fun checkLetter(){
        var word = motAtrouve.toList()
        var line = currentWord.toList()
        for(i in 0..word.size-1){
            val motLettre = line[i]
            val motAtrouverLettre = word[i]
            if(motAtrouverLettre == motLettre){
                println( motLettre+" bien placée")
                //lettre bien placée
            }
            else if(word.contains(motLettre) && motAtrouverLettre != motLettre){
                //lettre dans le mot mais pas bien placée
                println(motLettre+"dans le mot")
            }
            else{
                println(motLettre+" pas dans le mot")
                //lettre pas dans mot
            }
        }
    }

    private fun getWordOfTheDay(){
        viewModelScope.launch(Dispatchers.IO){
            val word = Word("poule", "1", "1")
            motAtrouve = word.name
            mState.value = State.success(word)
            initList()
//            try{
//                repository.getRandomWord().collect(){
//                    motAtrouve = it.name
//                    mState.value = State.success(it)
//                }
//            }catch(ex: Exception){
//                mState.value = State.failed(ex.localizedMessage)
//            }
        }
    }
}