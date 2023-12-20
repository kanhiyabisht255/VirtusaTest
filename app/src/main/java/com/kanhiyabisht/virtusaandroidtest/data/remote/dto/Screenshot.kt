package com.kanhiyabisht.virtusaandroidtest.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Screenshot(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?
)