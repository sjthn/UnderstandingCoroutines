package com.example.srijithn.understandingcoroutines

class ProductRepository {

    private val network: Network by lazy {
        Network()
    }

    private val database: Database by lazy {
        Database()
    }

    fun fetchProducts(): List<Product> {
        return network.fetchProducts()
    }

    fun storeProducts(products: List<Product>): Boolean {
        return database.storeProducts(products)
    }

}