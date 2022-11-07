package com.example.pazarama_bitirme_app.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.databinding.FragmentHomeBinding
import com.example.pazarama_bitirme_app.feature.home.adapter.HomeDetailAdapter
import com.example.pazarama_bitirme_app.feature.home.adapter.OnDetailProductClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint


    class HomeFragment : Fragment(), OnDetailProductClickListener {
        private val viewModel by viewModels<HomeViewModel>()
        private lateinit var binding: FragmentHomeBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            lifecycleScope.launchWhenResumed {
                launch {
                    viewModel.uiState.collect {
                        when (it) {
                            is HomeViewState.Success -> {
                                binding.rvHome.layoutManager = GridLayoutManager(context, 2)
                                binding.rvHome.adapter =
                                    HomeDetailAdapter(this@HomeFragment).apply {
                                       submitList(it.products)
                                    }
                            }
                            is HomeViewState.Loading -> {

                            }

                        }
                    }
                }

                launch {
                    viewModel.uiEvent.collect {
                        when (it) {
                            is HomeViewEvent.ShowError -> {
                                Snackbar.make(
                                    binding.root,
                                    it.message.toString(),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }


        override fun onProductClick(id: Int?) {
            TODO("Not yet implemented")
        }

        override fun onDetailClick(detail: Product) {
            TODO("Not yet implemented")
        }
    }


