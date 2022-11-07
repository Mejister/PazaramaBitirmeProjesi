package com.example.pazarama_bitirme_app.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.databinding.FragmentDetailBinding
import com.example.pazarama_bitirme_app.databinding.ProductListBinding
import com.example.pazarama_bitirme_app.feature.detail.DetailFragment

class HomeDetailAdapter(private val listener: OnDetailProductClickListener) :
    ListAdapter<Product, HomeDetailAdapter.DetailProductViewHolder>(DetailProductDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailProductViewHolder {
        return DetailProductViewHolder(
            ProductListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailProductViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class DetailProductViewHolder(private val binding: ProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Product, listener: OnDetailProductClickListener) {
            binding.dataHolder = data
           binding.listener = listener
            binding.root
        }
    }

    class DetailProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnDetailProductClickListener {
    fun onProductClick(id: Int?)
    fun onDetailClick(detail: Product)
}
