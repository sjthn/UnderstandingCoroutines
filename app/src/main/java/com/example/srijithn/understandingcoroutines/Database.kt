package com.example.srijithn.understandingcoroutines

import kotlinx.coroutines.delay
import java.util.*

class Database {

    suspend fun storeProducts(products: List<Product>): Result<Unit> {
        delay(1000)
        val random = Random().nextInt(10)
        if (random <= 0 || products.isEmpty()) {
            return Failure(ResultException("Storing in database failed"))
        }
        return Success(Unit)
    }

}