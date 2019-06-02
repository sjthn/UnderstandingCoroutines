package com.example.srijithn.understandingcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `simple blocking coroutine`() = runBlocking {
        launch {
            delay(1000)
            println("Coroutines!!!")
        }
        print("Starting ")
    }

}
