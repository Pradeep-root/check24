package com.pradeep.check24.data.network

import com.pradeep.check24.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {


    @GET("/products-test.json")
    suspend fun getProducts() : Response<ProductResponse>
}