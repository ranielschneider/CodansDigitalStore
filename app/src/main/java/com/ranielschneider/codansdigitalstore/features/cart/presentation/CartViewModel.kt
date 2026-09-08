package com.ranielschneider.codansdigitalstore.features.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranielschneider.codansdigitalstore.features.cart.domain.Cart
import com.ranielschneider.codansdigitalstore.features.cart.domain.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {

    private val _carts = MutableStateFlow<List<Cart>>(emptyList())
    val carts: StateFlow<List<Cart>> = _carts.asStateFlow()

    init {
        getCarts()
    }
    fun getCarts() {
        viewModelScope.launch {
            _carts.value = repository.getCarts()
        }
    }
}