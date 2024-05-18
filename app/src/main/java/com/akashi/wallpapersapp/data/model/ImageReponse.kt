package com.akashi.wallpapersapp.data.model
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Image>,
    @SerializedName("total_results")
    val totalResults: Int
)