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
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
     var users by mutableStateOf<List<User>>(emptyList())
         private set
    fun getUsers() {

        viewModelScope.launch {
            users = repository.getUsers()
        }
    }
}