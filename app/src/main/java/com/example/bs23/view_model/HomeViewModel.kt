package com.example.bs23.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val repository: HomeRepository ) :ViewModel(){


     var responseLiveData : MutableLiveData<GitHubResponse> =MutableLiveData()
     var repositoriesLiveData : MutableLiveData<List<Item>> =MutableLiveData()
    lateinit var list:LiveData<PagingData<Item>>
    fun fetRepositoryApi(query: String , sort: String, order: String, per_page: Int, page: Int) {

        viewModelScope.launch {
            responseLiveData.value=repository.fetchRepositoryApi(query , sort, order, per_page, page)
            repositoriesLiveData.value= responseLiveData.value?.items

        }


    }

     fun addRepository(repositoryItem: Item){
         viewModelScope.launch {
             repository.insertRepository(repositoryItem)
         }


    }

    fun  getRepositories(){
        repositoriesLiveData.value=repository.getRepositories()
    }


    fun loadData(query: String , sort: String, order: String, per_page: Int) {

         list=repository.fetchRepositoryApi(query , sort, order, per_page).cachedIn(viewModelScope)


    }

}