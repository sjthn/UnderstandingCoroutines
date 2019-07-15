package com.example.srijithn.understandingcoroutines

import java.util.*

class Database {

    fun storeProducts(products: List<Product>): Boolean {
        Thread.sleep(1000)
        println("Storing products...")
        val random = Random().nextInt(10)
        if (random <= 0 || products.isEmpty()) {
            throw ResultException("Storing in database failed")
        } else {
            println("Completed storing products")
            return true
        }
    }

}