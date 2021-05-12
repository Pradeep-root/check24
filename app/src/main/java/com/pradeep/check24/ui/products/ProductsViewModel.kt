package com.pradeep.check24.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.util.Resource
import com.pradeep.check24.data.model.ProductResponse
import com.pradeep.check24.repository.ProductProductRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repositoryImp: ProductProductRepositoryImp) : ViewModel(){


    private val _productLiveData = MutableLiveData<Resource<ProductResponse>>()
    val productLiveData :  LiveData<Resource<ProductResponse>> = _productLiveData


    fun getProducts(){
        _productLiveData.postValue(Resource.loading())
        viewModelScope.launch {
           val result =  repositoryImp.getProducts()
          _productLiveData.postValue(result)
        }
    }

}