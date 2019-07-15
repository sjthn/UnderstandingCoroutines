package com.example.srijithn.understandingcoroutines

import io.reactivex.Observable

class ProductRepository(private val network: Network, private val database: Database) {

    fun fetchProducts(): Observable<List<Product>> {
        return Observable.fromCallable { network.fetchProducts() }
    }

    fun storeProducts(products: List<Product>): Observable<Boolean> {
        return Observable.just(database.storeProducts(products))
    }

}