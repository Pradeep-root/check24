package com.pradeep.check24.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @SerializedName("products")
    val products: MutableList<Product>?
)