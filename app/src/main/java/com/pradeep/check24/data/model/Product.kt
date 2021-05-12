package com.pradeep.check24.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

    @SerializedName("name")
    val name : String?,

    @SerializedName("id")
    val id : Int?,

    @SerializedName("imageURL")
    val imageUrl : String?,

    @SerializedName("available")
    val available : Boolean?,

    @SerializedName("description")
    val description : String?,

    @SerializedName("price")
    val price : Price?,

    @SerializedName("rating")
    val rating : Double?,

    @SerializedName("longDescription")
    val longDescription : String?,

    val releaseDate : Long

) : Parcelable