package com.pradeep.check24.repository

import com.backbase.assignment.util.Resource
import com.pradeep.check24.data.model.ProductResponse

interface ProductRepository {

    suspend fun getProducts() : Resource<ProductResponse>
}