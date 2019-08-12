package com.example.srijithn.understandingcoroutines

class ProductRepository(private val network: Network, private val database: Database) {

    suspend fun fetchProducts(): Result<List<Product>> {
        return network.fetchProducts()
    }

    suspend fun storeProducts(products: List<Product>): Result<Unit> {
        return database.storeProducts(products)
    }

}