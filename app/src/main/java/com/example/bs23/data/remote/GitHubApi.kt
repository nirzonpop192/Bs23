package com.example.bs23.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {



    @GET("search/repositories")
    suspend fun getRepository(
        @Query("q") countryCode: String = "Android",
        @Query("sort") sort: String ,
        @Query("order") order: String ,
        @Query("per_page") per_page: Int ,
        @Query("page") page: Int ,
    ): Response<String>
}