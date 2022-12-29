package com.example.bs23.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val repository: HomeRepository ) :ViewModel(){


     var response : MutableLiveData<GitHubResponse> =MutableLiveData()

    fun fetRepositoryApi(query: String , sort: String, order: String, per_page: Int, page: Int) {
        viewModelScope.launch {
            response.value=repository.fetchRepositoryApi(query , sort, order, per_page, page)
//            if(response.value!!.items.size>0){
//                for (repositoryItem in response.value!!.items){
//                    Log.e("HomeViewModel","in loop")
//                    //repository.insertRepository(repositoryItem)
//                }
//            }

        }
    }
}