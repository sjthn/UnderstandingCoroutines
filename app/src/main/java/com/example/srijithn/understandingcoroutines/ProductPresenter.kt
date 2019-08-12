package com.example.srijithn.understandingcoroutines

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProductPresenter(
    private val repository: ProductRepository,
    private val scope: CoroutineScope
) {

    val loadingState = MutableLiveData<Boolean>().default(false)

    val products = MutableLiveData<List<Product>>()

    val error = MutableLiveData<String>()

    fun onFetchClick() {
        loadingState.value = true

        scope.launch {
            when (val fetchProductsResult = repository.fetchProducts()) {
                is Success -> {
                    val storeProductsResult = repository.storeProducts(fetchProductsResult.value)
                    if (storeProductsResult is Failure) onResultException(storeProductsResult.t.message)
                    else {
                        loadingState.value = false
                        products.value = fetchProductsResult.value
                    }
                }
                is Failure -> onResultException(fetchProductsResult.t.message)
            }
        }
    }

    private fun onResultException(message: String?) {
        loadingState.value = false
        error.value = message ?: "Unknown error"
    }

}

private fun <T> MutableLiveData<T>.default(defaultValue: T) = apply {
    value = defaultValue
}