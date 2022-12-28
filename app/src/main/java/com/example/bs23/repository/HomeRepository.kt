package com.example.bs23.repository

import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.remote.GitHubApi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor( private val api:GitHubApi) {

    suspend fun fetchRepositoryApi(query: String , sort: String, order: String, per_page: Int, page: Int ): GitHubResponse {
        return api.getRepository(query , sort, order, per_page, page)
    }

}