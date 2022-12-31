package com.example.bs23.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.bs23.data.local.GitRepositoryDao
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.data.remote.GitHubApi
import com.example.bs23.paging.RepoPagingSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val api:GitHubApi ,
    private  val doa:GitRepositoryDao) {
    companion object{
        private const val TAG="HomeRepository"
    }

    suspend fun fetchRepositoryApi(query: String , sort: String, order: String,
                                   per_page: Int, page: Int ): GitHubResponse {
        return api.getRepository(query , sort, order, per_page, page)
    }

     fun fetchRepositoryApi(query: String , sort: String, order: String,
                                   per_page: Int )= Pager(
         config = PagingConfig(pageSize = 10, maxSize = 50),
         pagingSourceFactory = {RepoPagingSource(api,doa,query , sort, order, per_page)}

     ).liveData

    suspend fun insertRepository(repository: Item){
       val  row=doa.insert(repository)
        Log.e(TAG, "row effected $row")
    }

    fun getRepositories() : List<Item> {
       return doa.getRepository()
    }

    suspend fun deleteAllRepositories (){
        doa.deleteAllRepository()
    }


}