package com.example.srijithn.understandingcoroutines

class ProductRepository {

    private val network: Network by lazy {
        Network()
    }

    private val database: Database by lazy {
        Database()
    }

    fun fetchProducts(presenterCallback: (Result<List<Product>>) -> Unit) {
        network.fetchProducts(presenterCallback)
    }

    fun storeProducts(products: List<Product>, presenterCallback: (Result<Boolean>) -> Unit) {
        database.storeProducts(products, presenterCallback)
    }

}