package com.example.nwavetask.allProduct.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.nwavetask.R
import com.example.nwavetask.allProduct.viewModel.AllProductsViewModel
import com.example.nwavetask.databinding.FragmentAllProductsBinding
import com.example.nwavetask.model.Product
import com.example.nwavetask.network.ProductAPi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AllProductsFragment : Fragment(), Transaction {
    private lateinit var binding: FragmentAllProductsBinding
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
        allProductsViewModel.getProducts()
        lifecycleScope.launch {
            var adapter =
                AllProductsAdapter(
                    ProductAPi.retrofitService.getProducts().body() ?: listOf(),
                    this@AllProductsFragment
                )
            binding.mAdapter = adapter
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

