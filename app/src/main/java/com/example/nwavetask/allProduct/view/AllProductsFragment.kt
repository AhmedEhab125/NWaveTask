package com.example.nwavetask.allProduct.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.nwavetask.R
import com.example.nwavetask.allProduct.viewModel.AllProductsViewModel
import com.example.nwavetask.databinding.FragmentAllProductsBinding
import com.example.nwavetask.model.Product
import com.example.nwavetask.model.ProductsModelItem
import com.example.nwavetask.network.ApiState
import com.example.nwavetask.network.NetworkListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllProductsFragment : Fragment(), Transaction {
    private lateinit var binding: FragmentAllProductsBinding
    lateinit var adapter: AllProductsAdapter
    val allProductsViewModel: AllProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_all_products, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AllProductsAdapter(listOf(), this)
        binding.mAdapter = adapter
        getData()

    }
    fun getData(){
        if(NetworkListener.getConnectivity(requireContext())){
            allProductsViewModel.getProductsFromApi()
            observeFromNetwork()
        }else{
            allProductsViewModel.getProductsFromDataBase()
            observeFromDatabase()
            Toast.makeText(requireContext(),"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }

    fun  observeFromNetwork() {
        lifecycleScope.launch {
            allProductsViewModel.accessProductsData.collect { result ->
                when (result) {
                    is ApiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Success<*> -> {
                        binding.progressBar.visibility = View.GONE


                       val  productsList = result.data as List<ProductsModelItem>

                        adapter.setProducts(productsList)
                    }
                    is ApiState.Failure -> {
                        binding.progressBar.visibility = View.GONE

                    }
                }

            }
        }

    }
    fun observeFromDatabase() {
        lifecycleScope.launch {
            allProductsViewModel.accessLocalProductsData.collect { result ->
                when (result) {
                    is ApiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Success<*> -> {
                        binding.progressBar.visibility = View.GONE
                        val productsList = result.data as List<Product>
                        var products = mutableListOf<ProductsModelItem>()
                        productsList.forEach {
                            products.add(ProductsModelItem(it, listOf()))
                        }
                        adapter.setProducts(products)
                    }
                    is ApiState.Failure -> {
                        binding.progressBar.visibility = View.GONE

                    }
                }

            }
        }

    }

    override fun moveToDetailsScreen(product: Product) {
        val bundle = Bundle().apply {
            putSerializable("productDetails", product)
        }
        Navigation.findNavController(requireView())
            .navigate(R.id.action_allProductsFragment_to_produuctDetailsFragment, bundle)
    }


}

