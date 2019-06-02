package com.example.srijithn.understandingcoroutines

import androidx.lifecycle.MutableLiveData

class ProductPresenter {

    private val repository: ProductRepository by lazy {
        ProductRepository()
    }

    val loadingState = MutableLiveData<Boolean>().default(false)

    val products = MutableLiveData<List<Product>>()

    val error = MutableLiveData<String>()

    fun onFetchClick() {
        loadingState.value = true

        repository.fetchProducts { result ->
            when {
                result.isSuccess -> {
                    products.postValue(result.getOrDefault(listOf()))
                }
                result.isFailure -> {
                    error.postValue(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
            loadingState.postValue(false)
        }
    }
}

private fun <T> MutableLiveData<T>.default(defaultValue: T) = apply {
    value = defaultValue
}