package com.kisanthapa.mvvm.api

import com.kisanthapa.mvvm.models.NewsResponse
import com.kisanthapa.mvvm.utility.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


    @GET("top-everything")
    suspend fun searchForNews(
        @Query("q")
        countryCode: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


}