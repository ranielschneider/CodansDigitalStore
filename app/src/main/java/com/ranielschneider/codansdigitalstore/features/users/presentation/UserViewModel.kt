package com.ranielschneider.codansdigitalstore.features.users.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ranielschneider.codansdigitalstore.features.users.domain.User
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepository
import java.util.Collections.emptyList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

     var users by mutableStateOf<List<User>>(emptyList())
         private set
    fun getUsers() {

        viewModelScope.launch {
            users = repository.getUsers()
        }
    }

    fun getUserById(id: Int ){
        viewModelScope.launch {
            try {
                _user.value = repository.getUserById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}