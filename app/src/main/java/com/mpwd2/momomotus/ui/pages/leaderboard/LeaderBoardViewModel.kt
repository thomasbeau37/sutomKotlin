package com.mpwd2.momomotus.ui.pages.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.data.entities.User
import com.mpwd2.momomotus.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    init{
        getAllUser()
    }

    private val mState = MutableStateFlow<State<List<User>>>(State.loading())
    val state: MutableStateFlow<State<List<User>>>
        get() = mState


    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                userRepository.getAllUsers().collect { it ->
                    it?.let {
                            if (it != null) {
                                mState.value = State.success(it)
                            } else {
                                mState.value = State.failed("error")
                            }
                    }
                }
            }catch (ex : Exception){
                mState.value = State.failed(ex.localizedMessage)
            }
        }
    }
    }
