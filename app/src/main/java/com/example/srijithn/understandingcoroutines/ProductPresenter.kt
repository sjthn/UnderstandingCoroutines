package com.example.srijithn.understandingcoroutines

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductPresenter(private val repository: ProductRepository) {

    val loadingState = MutableLiveData<Boolean>().default(false)

    val products = MutableLiveData<List<Product>>()

    val error = MutableLiveData<String>()

    private val disposable = CompositeDisposable()

    fun onFetchClick() {
        loadingState.value = true

        disposable.add(
            repository.fetchProducts()
                .map { productsResponse ->
                    repository.storeProducts(productsResponse).subscribe()
                    productsResponse
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ productsResponse ->
                    loadingState.postValue(false)
                    products.postValue(productsResponse)
                }, { throwable ->
                    loadingState.postValue(false)
                    error.postValue(throwable.message)
                })
        )
    }

    fun onDestroy() {
        disposable.clear()
    }
}

private fun <T> MutableLiveData<T>.default(defaultValue: T) = apply {
    value = defaultValue
}