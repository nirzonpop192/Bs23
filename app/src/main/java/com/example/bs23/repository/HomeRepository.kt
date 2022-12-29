package com.example.bs23.repository

import android.util.Log
import com.example.bs23.data.local.GitRepositoryDao
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.data.remote.GitHubApi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor( private val api:GitHubApi ,
                private  val doa:GitRepositoryDao) {
    companion object{
        private const val TAG="HomeRepository"
    }

    suspend fun fetchRepositoryApi(query: String , sort: String, order: String, per_page: Int, page: Int ): GitHubResponse {
        return api.getRepository(query , sort, order, per_page, page)
    }

//    suspend fun insertRepository(repository: Item){
//       val  row=doa.insert(repository)
//        Log.e(TAG,"row effected "+row)
//    }

}