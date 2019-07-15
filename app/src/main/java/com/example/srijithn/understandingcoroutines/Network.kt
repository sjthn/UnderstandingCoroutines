package com.example.srijithn.understandingcoroutines

import kotlin.random.Random

class Network {

    fun fetchProducts(): List<Product> {
        Thread.sleep(2000)
        return fetchFromNetwork()
    }

    private fun fetchFromNetwork(): List<Product> {
        val count = Random.nextInt(10)
        if (count == 0)
            throw ResultException("Count must be greater than 0")
        else {
            val products = mutableListOf<Product>()
            for (i in 0..count) {
                val product = Product(i.toString(), "Product $i", "http://google.com/$i")
                products += product
            }
            return products
        }
    }

}