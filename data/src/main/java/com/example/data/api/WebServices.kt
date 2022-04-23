package com.example.data.api

import com.example.data.model.NewsResponse
import com.example.data.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources") //request
    suspend fun getSources(
        @Query("apiKey")apiKey:String,
        @Query("category")category:String
    ) : SourceResponse //response

    @GET("v2/everything")
    suspend fun getNewsResources(
        @Query("apiKey")apiKey : String,
        @Query("sources")sources:String
    ): NewsResponse


    @GET("v2/everything")
    fun searchNewsResources(
        @Query("apiKey")apiKey : String,
        @Query("q")q : String,
        @Query("sources")sources:String
    ):Call<NewsResponse>
}