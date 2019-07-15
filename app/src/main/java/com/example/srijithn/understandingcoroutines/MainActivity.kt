package com.example.srijithn.understandingcoroutines

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProductPresenter(ProductRepository(Network(), Database()))

        init()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun init() {

        button_fetch.setOnClickListener {
            presenter.onFetchClick()
        }

        observeLoadingState()

        observeProducts()

        observeErrorState()
    }

    private fun observeErrorState() {
        presenter.error.observeNonNull(this) { message ->
            textview_products.text = message
        }
    }

    private fun observeProducts() {
        presenter.products.observeNonNull(this) { products ->
            textview_products.text = products.joinToString {
                it.name
            }
        }
    }

    private fun observeLoadingState() {
        presenter.loadingState.observeNonNull(this) { shouldShow ->
            progress_bar_loading.visibility = if (shouldShow) VISIBLE else GONE
        }
    }
}

private fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, onChange: (data: T) -> Unit) {
    observe(owner, Observer { newData ->
        if (newData != null) onChange(newData)
    })
}
