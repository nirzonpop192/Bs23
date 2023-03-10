package com.example.bs23.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.data.model.License
import com.example.bs23.data.model.Owner
import com.example.bs23.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val repository: HomeRepository ) :ViewModel(),ServiceInvokinListerner{

 companion object{
     var response : MutableLiveData<GitHubResponse> =MutableLiveData()

 }
     var isLoading:MutableLiveData<Boolean> =MutableLiveData()
    lateinit var pagingDataList:LiveData<PagingData<Item>>


     fun addRepository(repositoryItem: Item){
         viewModelScope.launch {
             repository.insertRepository(repositoryItem)
         }

     }
    fun addLicense(license: License){
        viewModelScope.launch {
            repository.insertLicense(license)
        }

    }


    fun addOwner(owner: Owner){
        viewModelScope.launch {
            repository.insertOwner(owner)
        }

    }


    fun deleteAllRecords(){
        viewModelScope.launch {
            repository.deleteAllRepositories()
            repository.deleteAllLicense()
            repository.deleteAllOwner()
        }
    }



    /**
     * load method invoke the pager
     */
    fun loadData(query: String , sort: String, order: String, per_page: Int) {
        isLoading.value=true
        viewModelScope.launch {
            pagingDataList=repository.fetchRepositoryApi(query , sort, order, per_page).cachedIn(viewModelScope)

        }

    }

    override fun invokeApi(query: String, sort: String, order: String, per_page: Int) {
        pagingDataList=repository.fetchRepositoryApi(query , sort, order, per_page).cachedIn(viewModelScope)

    }
}