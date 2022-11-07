package com.example.pazarama_bitirme_app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import com.example.pazarama_bitirme_app.domain.repository.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


    @HiltViewModel
    class HomeViewModel @Inject constructor(
        private val productRepository: ProductRepository,
        private val firebaseAuth: FirebaseAuth,
        private val fireStore: FirebaseFirestore
    ) :
        ViewModel() {
        private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.Success(mutableListOf()))
        val uiState: StateFlow<HomeViewState> = _uiState

        private val _uiEvent = MutableSharedFlow<HomeViewEvent>(replay = 0)
        val uiEvent: SharedFlow<HomeViewEvent> = _uiEvent

        init {
            getAllProducts()
        }

        private fun getAllProducts() {
            viewModelScope.launch {
                productRepository.getProductList().collect {
                    when (it) {
                        is DataState.Success -> {
                            _uiState.value = HomeViewState.Success(it.data?.map {
                                val data = getProductList(it?.id).first()

                                Product(
                                    id = it?.id,
                                    title = it?.title,
                                    category = it?.category,
                                    price = it.price,
                                    rating = it.rating,
                                    description = it.description,
                                    image = it.image
                                )
                            }?.toMutableList())
                        }
                        is DataState.Error -> {
                            _uiEvent.emit(HomeViewEvent.ShowError(it.error?.status_message))
                        }
                        is DataState.Loading -> {
                            _uiState.value = HomeViewState.Loading
                        }
                    }

                }
            }
        }

        private fun getProductList(id: Int?): Flow<MutableList<String>?> = channelFlow {
            val favoriteList = mutableListOf<String>()
            val callBack =
                fireStore.collection("productId").document(firebaseAuth.currentUser?.uid.toString())
                    .collection("product").document(id.toString()).get().addOnSuccessListener {
                        it.data?.values?.forEach { data ->
                            favoriteList.add(data.toString())
                        }
                        trySendBlocking(favoriteList)

                    }.addOnFailureListener {
                        trySendBlocking(mutableListOf())
                    }
            awaitClose { callBack.isCanceled() }
        }


        private fun insertProduct(userId: String, data: Product) {
            fireStore.collection("productId").document(userId.toString()).collection("product")
                .let { ref ->
                    ref.document("${data.id}")
                        .set(
                            mapOf(
                                "productId" to "${data.id}",
                                "title" to data.title,
                                "description" to data.description,
                                "image" to data.image,
                                "price" to data.price,
                                "rating" to data.rating,
                                "category" to data.category
                            )
                        )

                        }
                        .addOnFailureListener { error ->
                            viewModelScope.launch {
                                _uiEvent.emit(HomeViewEvent.ShowError(error.message.toString()))

                            }
                        }
                }
        }


    sealed class HomeViewEvent {
        data class ShowError(val message: String?) : HomeViewEvent()
    }

    sealed class HomeViewState {
        class Success(val products: MutableList<Product>?) : HomeViewState()
        object Loading : HomeViewState()
    }
