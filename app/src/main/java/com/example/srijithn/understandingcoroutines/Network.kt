package com.example.srijithn.understandingcoroutines

import kotlinx.coroutines.delay
import kotlin.random.Random

class Network {

    suspend fun fetchProducts(): Result<List<Product>> {
        return try {
            delay(2000)
            val products = fetchFromNetwork()
            Success(products)
        } catch (e: ResultException) {
            Failure(e)
        }
    }

    private fun fetchFromNetwork(): List<Product> {
        val count = Random.nextInt(10)
        if (count == 0) throw ResultException("Count must be greater than 0")
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