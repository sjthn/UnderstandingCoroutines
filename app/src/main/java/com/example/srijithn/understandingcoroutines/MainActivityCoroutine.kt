package com.example.srijithn.understandingcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.random.Random

//class MainActivityCoroutine : AppCompatActivity() {
//
//    private lateinit var presenter: ProductPresenter
//
//    override fun onFetchClick(savedInstanceState: Bundle?) {
//        super.onFetchClick(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        presenter = ProductPresenter()
//
//        presenter.onViewCreate()
//
//        val start = System.currentTimeMillis()
//
//        loading(true)
//
//        fetchProductsAsync()
//        fetchProducts { result ->
//            when {
//                result.isSuccess -> showProducts(result.getOrNull())
//                result.isFailure -> println(result.exceptionOrNull())
//            }
//            loading(false)
//            val time = System.currentTimeMillis() - start
//            println("time: $time")
//        }
//    }
//
//    private fun showProducts(result: List<Product>?) {
//        println("Products: $result")
//    }
//
//    private fun loading(shouldShow: Boolean) = println("shouldShow $shouldShow")
//}
//
//
//class ProductPresenter {
//
//    fun onViewCreate() {
//
//    }
//
//}
//
//
//// ProductRepository.kt
//fun fetchProductsAsync() {
//    GlobalScope.launch {
//        NetworkCallAsync().fetchProductsAsync()
//    }
//}
//
//private val executor = Executors.newFixedThreadPool(2)
//
//// network
//open class NetworkCallAsync {
//    fun fetchProducts(callback: (Result<List<Product>>) -> Unit) {
//        executor.submit {
//            Thread.sleep(2000)
//
//            fetchFromNetwork(callback)
//        }
//    }
//
//    suspend fun fetchProductsAsync(): List<Product> {
//        delay(2000)
//        return fetchFromNetwork()
//    }
//
//    private fun fetchFromNetwork(): List<Product> {
//        val count = Random.nextInt(10)
//        if (count == 0) throw ResultException("Count must be greater than 0")
//        else {
//            val products = parseProducts(count)
//            return products
//        }
//    }
//
//    private fun parseProducts(count: Int): List<Product> {
//        val products = mutableListOf<Product>()
//        for (i in 0..count) {
//            val product = Product(i.toString(), "Product $i", "http://google.com/$i")
//            products += product
//        }
//        return products
//    }
//}
//
//
//class ResultException(msg: String) : Exception(msg)
//
//
//data class Product(val id: String, val name: String, val deepLink: String)
