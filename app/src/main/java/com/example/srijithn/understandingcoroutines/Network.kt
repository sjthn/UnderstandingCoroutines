package com.example.srijithn.understandingcoroutines

import java.util.concurrent.Executors
import kotlin.random.Random

class Network {

    private val backgroundThread = Executors.newFixedThreadPool(2)

    fun fetchProducts(callback: (Result<List<Product>>) -> Unit) {
        backgroundThread.submit {
            Thread.sleep(2000)

            fetchFromNetwork(callback)
        }
    }

    private fun fetchFromNetwork(callback: (Result<List<Product>>) -> Unit) {
        val count = Random.nextInt(10)
        if (count == 0) callback(Result.failure(ResultException("Count must be greater than 0")))
        else {
            val products = mutableListOf<Product>()
            for (i in 0..count) {
                val product = Product(i.toString(), "Product $i", "http://google.com/$i")
                products += product
            }
            callback(Result.success(products))
        }
    }

}