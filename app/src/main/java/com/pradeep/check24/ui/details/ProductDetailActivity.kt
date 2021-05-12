package com.pradeep.check24.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pradeep.check24.R
import com.pradeep.check24.data.model.Product
import com.pradeep.check24.utils.Constants.Companion.PRODUCT_PUT_EXTRA_KEY
import com.pradeep.check24.utils.DateUtil
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.product_item.view.*
import java.util.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        setupProductDetailObserver()
        getPutExtraArgument()
    }

    private fun setupProductDetailObserver() {
        viewModel.productDetailLiveData.observe(this, Observer {
           it?.let { product ->
               setProductDetails(product)
           }
        })
    }

    private fun setProductDetails(product: Product){
        tv_deail_name.text = product.name
        tv_longDescription.text = product.longDescription
        tv_price.text = "Preis ${product.price?.value} ${product.price?.currency}"
        rating_product.rating = product.rating?.toFloat()!!
        tv_releaseDate.text = DateUtil.getDateTime(product.releaseDate)

        Glide.with(img_product)
            .load(product.imageUrl)
            .placeholder(getDrawable(R.drawable.ic_baseline_image_24))
            .error(getDrawable(R.drawable.ic_baseline_image_not_supported_24))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(img_product)
    }

    private fun getPutExtraArgument(){
        val product =  intent.getParcelableExtra<Product>(PRODUCT_PUT_EXTRA_KEY)
        product?.let {
            viewModel.setProduct(it)
            Log.i("product", it.toString())
        }
    }
}