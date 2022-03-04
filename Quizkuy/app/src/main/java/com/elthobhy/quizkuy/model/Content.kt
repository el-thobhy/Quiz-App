package com.elthobhy.quizkuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(

	@field:SerializedName("contents")
	val contents: List<Contents>? = null
) : Parcelable