package com.mpwd2.momomotus.ui.pages.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.data.entities.User
import com.mpwd2.momomotus.data.repositories.AuthRepository
import com.mpwd2.momomotus.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {


    private val mSignUpState = MutableStateFlow<State<User>>(State.loading())
    val signUpState: StateFlow<State<User>>
        get() = mSignUpState

    fun login(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                authRepository.login(email, password).collect { it ->
                    it?.let { it ->
                        userRepository.getUser(it.uid).collect {
                            if (it != null) {
                                mSignUpState.value = State.success(it)
                            } else {
                                mSignUpState.value = State.failed("error")
                            }
                        }
                    }
                }
            }catch (ex : Exception){
                mSignUpState.value = State.failed(ex.localizedMessage)
            }
        }
    }
}

