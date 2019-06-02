package com.example.srijithn.understandingcoroutines

import java.util.*

class Database {

    fun storeProducts(products: List<Product>, callback: (Result<Boolean>) -> Unit) {
        backgroundThread.submit {
            println("Storing products...")
            Thread.sleep(1000)
            val random = Random().nextInt(10)
            if (random <= 0 || products.isEmpty()) {
                callback(Result.failure(ResultException("Storing in database failed")))
            } else {
                callback(Result.success(true))
            }
            println("Completed storing products")
        }
    }

}