package com.elthobhy.quizkuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answers(

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("correct_answer")
	val correctAnswer: Boolean? = null,

	@field:SerializedName("is_click")
	var isClick: Boolean? = null,

	@field:SerializedName("option")
	val option: String? = null
) : Parcelable