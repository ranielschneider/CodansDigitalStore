package com.ranielschneider.codansdigitalstore.features.posts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranielschneider.codansdigitalstore.features.posts.domain.Post
import com.ranielschneider.codansdigitalstore.features.posts.domain.repository.PostRepository
import com.ranielschneider.codansdigitalstore.features.users.domain.User
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set

    var users by mutableStateOf<List<User>>(emptyList())
        private set

    fun getPosts() {
        viewModelScope.launch {
            posts = repository.getPosts()
            users = userRepository.getUsers()
        }
    }
}