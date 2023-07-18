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
        onRefresh()
    }

    fun getData() {
        if (NetworkListener.getConnectivity(requireContext())) {
            allProductsViewModel.getProductsFromApi()
            observeFromNetwork()
        } else {
            allProductsViewModel.getProductsFromDataBase()
            observeFromDatabase()
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }

    }

    fun observeFromNetwork() {
        lifecycleScope.launch {
            allProductsViewModel.accessProductsData.collect { result ->
                binding.swiperefresh.isRefreshing = false
                when (result) {

                    is ApiState.Loading -> {
                        binding.recyclerView.visibility=View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Success<*> -> {
                        binding.progressBar.visibility = View.GONE


                        val productsList = result.data as List<ProductsModelItem>
                        if (productsList.isEmpty())
                            binding.lottieSplash.visibility = View.VISIBLE
                        else
                            binding.lottieSplash.visibility = View.GONE

                        adapter.setProducts(productsList)
                        binding.recyclerView.visibility=View.VISIBLE
                    }
                    is ApiState.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Some thing went wrong", Toast.LENGTH_LONG).show()

                    }
                }

            }
        }

    }

    fun observeFromDatabase() {
        lifecycleScope.launch {
            allProductsViewModel.accessLocalProductsData.collect { result ->
                binding.swiperefresh.isRefreshing = false
                binding.progressBar.visibility = View.GONE
                var products = mutableListOf<ProductsModelItem>()
                for (product in  result ){
                var productItem =ProductsModelItem(product, listOf())
                    products.add(productItem)
                }
                if (products.isEmpty())
                    binding.lottieSplash.visibility = View.VISIBLE
                else
                    binding.lottieSplash.visibility = View.GONE
                adapter.setProducts(products)

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
    fun onRefresh(){
        binding.swiperefresh.setOnRefreshListener {
            getData()
        }
    }


}

