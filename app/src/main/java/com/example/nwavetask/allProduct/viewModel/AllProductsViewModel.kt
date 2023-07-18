package com.example.nwavetask.allProduct.viewModel

import androidx.lifecycle.ViewModel
import com.example.nwavetask.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AllProductsViewModel @Inject constructor (private  val repo : IRepository) : ViewModel() {
fun getProducts(){
    println("doneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
}
}