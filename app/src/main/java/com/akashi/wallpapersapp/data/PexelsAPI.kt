package com.akashi.wallpapersapp.data

import com.akashi.wallpapersapp.BuildConfig
import com.akashi.wallpapersapp.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsAPI {

    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("search")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 40,
        @Query("orientation") orientation: String = "portrait"
    ): ImageResponse

    
}