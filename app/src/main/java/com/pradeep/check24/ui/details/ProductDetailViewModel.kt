package com.pradeep.check24.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradeep.check24.data.model.Product

class ProductDetailViewModel : ViewModel() {


    private val _productDetailLiveData = MutableLiveData<Product>()
    val productDetailLiveData : LiveData<Product> = _productDetailLiveData

    fun setProduct(product: Product){
        _productDetailLiveData.postValue(product)
    }
}