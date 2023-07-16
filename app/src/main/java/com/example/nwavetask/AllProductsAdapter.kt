package com.example.nwavetask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nwavetask.databinding.ProductItemBinding
import com.example.nwavetask.model.ProductsModelItem


class AllProductsAdapter(private var products : List<ProductsModelItem> ) : RecyclerView.Adapter<AllProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item,
            parent,
            false
            )
       return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val productImg = products[position].Product.image_url.replace("http://", "https://")
        products[position].Product.image_url = productImg
      holder.binding.product =   products[position].Product
    }

    override fun getItemCount(): Int {
      return  products.size
    }
    class ViewHolder(var binding : ProductItemBinding): RecyclerView.ViewHolder(binding.root)

}