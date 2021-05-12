package com.pradeep.check24.repository

import com.backbase.assignment.util.Resource
import com.pradeep.check24.data.model.ProductResponse
import com.pradeep.check24.data.network.ProductApi
import java.io.IOException
import javax.inject.Inject

class ProductProductRepositoryImp @Inject constructor(private val productApi: ProductApi)  : ProductRepository{


    override suspend fun getProducts(): Resource<ProductResponse> {
        return  try {
            val response = productApi.getProducts()
            val result = response.body()

            if(response.isSuccessful && result != null){
                Resource.success(result)
            }else{
                Resource.error(response.message(), null)
            }

        }catch (e : IOException){
            Resource.error( e.message.toString(), null)
        }
    }

}