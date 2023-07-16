package com.example.nwavetask.AllProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.nwavetask.AllProductsAdapter
import com.example.nwavetask.databinding.FragmentAllProductsBinding
import com.example.nwavetask.network.ProductAPi
import kotlinx.coroutines.launch


class AllProductsFragment : Fragment() {
lateinit var binding : FragmentAllProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,
            com.example.nwavetask.R.layout.fragment_all_products,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launch {
       var adapter = AllProductsAdapter( ProductAPi.retrofitService.getProducts().body() ?: listOf())
        binding.mAdapter = adapter
    }
    }

}

