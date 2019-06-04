package com.example.srijithn.understandingcoroutines

import java.util.*

class Database {

    fun storeProducts(products: List<Product>): Boolean {
        var isSuccessful: Boolean
        println("Storing products...")
        Thread.sleep(1000)
        val random = Random().nextInt(10)
        if (random <= 0 || products.isEmpty()) {
            throw ResultException("Storing in database failed")
        } else {
            isSuccessful = true
        }
        println("Completed storing products")
        return isSuccessful
    }

}