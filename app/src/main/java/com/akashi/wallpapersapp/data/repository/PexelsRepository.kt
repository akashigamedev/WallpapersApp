package com.akashi.wallpapersapp.data.repository

import com.akashi.wallpapersapp.data.PexelsAPI
import com.akashi.wallpapersapp.data.model.ImageResponse
import com.akashi.wallpapersapp.utils.Resource
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PexelsRepository @Inject constructor(
    private val api: PexelsAPI
) {

    suspend fun getImages(query: String): Resource<ImageResponse> {
        val response = try {
            api.getImages(query)
        } catch(e: Exception) {
            when(e) {
                is IOException -> return Resource.Error(
                    "Couldn't connect to the internet"
                )
                is HttpException -> return Resource.Error(
                    "We're unable to reach servers. Please retry."
                )
                else -> return Resource.Error(
                    e.localizedMessage ?: "An unknown error occurred. Please retry."
                )
            }
        }
        return Resource.Success(response)
    }


    suspend fun getCuratedImages(page: Int): Resource<ImageResponse> {
        val response = try {
            api.getCuratedImages(page)
        } catch(e: Exception) {
            when(e) {
                is IOException -> return Resource.Error(
                    "Couldn't connect to the internet"
                )
                is HttpException -> return Resource.Error(
                    "We're unable to reach servers. Please retry."
                )
                else -> return Resource.Error(
                    e.localizedMessage ?: "An unknown error occurred. Please retry."
                )
            }
        }
        return Resource.Success(response)
    }

}