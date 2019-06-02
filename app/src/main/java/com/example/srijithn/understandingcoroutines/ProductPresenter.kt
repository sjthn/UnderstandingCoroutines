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
                    repository.storeProducts(result.getOrDefault(listOf())) { dbResult ->
                        loadingState.postValue(false)
                        when {
                            dbResult.isSuccess -> products.postValue(result.getOrDefault(listOf()))
                            dbResult.isFailure -> error.postValue(
                                dbResult.exceptionOrNull()?.message ?: "Unknown error"
                            )
                        }
                    }
                }
                result.isFailure -> {
                    loadingState.postValue(false)
                    error.postValue(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
        }
    }
}

private fun <T> MutableLiveData<T>.default(defaultValue: T) = apply {
    value = defaultValue
}