package com.example.nwavetask.allProduct.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nwavetask.model.Product
import com.example.nwavetask.network.ApiState
import com.example.nwavetask.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(private val repo: IRepository) : ViewModel() {
    private var _networkProductsData = MutableStateFlow<ApiState>(ApiState.Loading)
    var accessProductsData: StateFlow<ApiState> = _networkProductsData
    private var _localProductsData = MutableStateFlow<List<Product>>(listOf())
    var accessLocalProductsData: StateFlow<List<Product>> = _localProductsData
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _networkProductsData.value = ApiState.Failure(throwable)
        throwable.printStackTrace()
    }
    fun getProductsFromApi() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var products = repo.getProductsFromApi()
            _networkProductsData.value = products
        }
    }
    fun getProductsFromDataBase(){
        viewModelScope.launch(Dispatchers.IO+ coroutineExceptionHandler) {
             repo.getProductsFromDataBase().collect{
                 _localProductsData.value = it
            }
        }
    }
}