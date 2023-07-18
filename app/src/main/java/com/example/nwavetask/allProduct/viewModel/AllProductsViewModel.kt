package com.example.nwavetask.allProduct.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nwavetask.network.ApiState
import com.example.nwavetask.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(private val repo: IRepository) : ViewModel() {
    private var _productsData = MutableStateFlow<ApiState>(ApiState.Loading)
    var accessProductsData: StateFlow<ApiState> = _productsData

    fun getProductsFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            var products = repo.getProductsFromApi()
            _productsData.value = products
        }
    }
    fun getProductsFromDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
             repo.getProductsFromDataBase().catch{e->
                 _productsData.value = ApiState.Failure(e)
            }.collect{
                 _productsData.value = ApiState.Success(it)
            }
        }
    }
}