package com.example.srijithn.understandingcoroutines

class ProductRepository {

    private val network: Network by lazy {
        Network()
    }

    fun fetchProducts(presenterCallback: (Result<List<Product>>) -> Unit) {
        network.fetchProducts { result ->
            presenterCallback(result)
        }
    }

}