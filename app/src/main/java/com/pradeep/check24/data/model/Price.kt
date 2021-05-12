package com.pradeep.check24.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val value : Double?,
    val currency: String?
): Parcelable