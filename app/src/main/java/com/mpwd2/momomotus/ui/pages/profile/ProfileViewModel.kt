package com.mpwd2.momomotus.ui.pages.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.data.entities.User
import com.mpwd2.momomotus.data.entities.Word
import com.mpwd2.momomotus.data.repositories.UserRepository
import com.mpwd2.momomotus.data.repositories.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    init{
    }

    private lateinit var motAtrouve : String
    private val mState = MutableStateFlow<State<User>>(State.loading())
    val state: MutableStateFlow<State<User>>
        get() = mState


    fun getUser(): User {
        return userRepository.getCurrentUser()
    }
}