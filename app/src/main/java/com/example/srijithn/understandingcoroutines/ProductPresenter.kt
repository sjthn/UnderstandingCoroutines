package com.example.srijithn.understandingcoroutines

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData

class ProductPresenter {

    private val repository: ProductRepository by lazy {
        ProductRepository()
    }

    private val loadingState = MutableLiveData<Boolean>().default(false)

    private val products = MutableLiveData<List<Product>>()

    private val error = MutableLiveData<String>()

    val mainActivityState = MainActivityState(
        loadingState, products, error
    )

    fun onFetchClick() {
        FetchProductTask(mainActivityState, repository).execute()
    }

    class FetchProductTask(
        private val mainActivityState: MainActivityState,
        private val repository: ProductRepository
    ) :
        AsyncTask<MainActivityState, Void, List<Product>>() {

        override fun onPreExecute() {
            mainActivityState.loadingState.value = true
        }

        override fun doInBackground(vararg state: MainActivityState): List<Product> {
            return try {
                val products = repository.fetchProducts()
                repository.storeProducts(products)
                mainActivityState.products.postValue(products)
                products
            } catch (e: ResultException) {
                mainActivityState.error.postValue(e.message)
                listOf()
            }
        }

        override fun onPostExecute(result: List<Product>) {
            mainActivityState.loadingState.value = false
        }

    }
}

private fun <T> MutableLiveData<T>.default(defaultValue: T) = apply {
    value = defaultValue
}

data class MainActivityState(
    val loadingState: MutableLiveData<Boolean>,
    val products: MutableLiveData<List<Product>>,
    val error: MutableLiveData<String>
)