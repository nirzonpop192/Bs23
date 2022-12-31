package com.example.bs23.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.bs23.data.local.GitRepositoryDao
import com.example.bs23.data.local.LicenseDao
import com.example.bs23.data.local.OwnerDao
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.data.model.License
import com.example.bs23.data.model.Owner
import com.example.bs23.data.remote.GitHubApi
import com.example.bs23.paging.RepoPagingSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val api:GitHubApi,
    private  val repositoryDao:GitRepositoryDao,
    private  val licenseDao:LicenseDao,
    private  val ownerDao: OwnerDao,
) {
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
         pagingSourceFactory = {RepoPagingSource(api,repositoryDao,query , sort, order, per_page)}

     ).liveData

    suspend fun insertRepository(repository: Item){
       val  row=repositoryDao.insert(repository)
        Log.e(TAG, "row effected $row")
    }


    suspend fun insertLicense(license: License){
        val  row=licenseDao.insert(license)
        Log.e(TAG, "row effected for license $row")
    }
    suspend fun insertOwner(owner: Owner){
        val  row=ownerDao.insert(owner)
        Log.e(TAG, "row effected for owner $row")
    }

    fun getRepositories() : List<Item> {
       return repositoryDao.getRepository()
    }

    suspend fun deleteAllRepositories (){
        repositoryDao.deleteAll()
    }
    suspend fun deleteAllLicense (){
        licenseDao.deleteAll()
    }

    suspend fun deleteAllOwner (){
        ownerDao.deleteAll()
    }


}