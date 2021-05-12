package com.pradeep.check24.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.util.Status
import com.pradeep.check24.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var productAdapter: ProductsAdapter

    companion object{
         val  TAG = ProductsActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        viewModel.getProducts()

        setupProductObserver()
        setupProductList()
    }

    private fun setupProductList() {
        productAdapter = ProductsAdapter(arrayListOf())
        rv_product_list.apply {
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL, false)
//            val dividerItemDecorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
//            getDrawable(R.drawable.product_item_devider)?.let { dividerItemDecorator.setDrawable(it) }
//            addItemDecoration(dividerItemDecorator)
            adapter = productAdapter
        }
    }


    private fun setupProductObserver() {
        viewModel.productLiveData.observe(this, Observer { response ->
            response?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "${resource.data}")
                        resource.data?.products?.let { productAdapter.updateList(it) }
                    }

                    Status.ERROR -> {
                        Log.d(TAG, "${resource.status}")
                    }

                    Status.LOADING -> {
                        Log.d(TAG, "${resource.status}")
                    }
                }
            }
        })
    }
}