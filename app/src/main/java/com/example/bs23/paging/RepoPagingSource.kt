package com.example.bs23.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bs23.data.local.GitRepositoryDao
import com.example.bs23.data.model.Item
import com.example.bs23.data.remote.GitHubApi
import com.example.bs23.util.Constant
import com.example.bs23.view_model.HomeViewModel

class RepoPagingSource(private val api: GitHubApi,private  val doa: GitRepositoryDao, private val query: String,
                       private val  sort: String, private val  order: String,
                       private val per_page: Int) :PagingSource<Int,Item>(){

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        if(state.anchorPosition!=null)
        {
            val  anchorPage= state.closestPageToPosition(state.anchorPosition!!)
            if (anchorPage?.prevKey!=null)
                return anchorPage.prevKey!!.plus(1)
            else if (anchorPage?.nextKey!=null)
                return anchorPage.nextKey!!.minus(1)
            else return null

        }else
            return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        val pagePosition = params.key ?: 1
        // on line mode
        if (Constant.IS_NETWORK_AVAILABLE){
                val response = api.getRepository(query, sort, order, per_page, pagePosition)
                HomeViewModel.response.value = response
                return LoadResult.Page(
                    data = response.items,
                    prevKey = if (pagePosition == 1) null else pagePosition - 1,
                    nextKey = if (pagePosition == 100) null else pagePosition + 1
                )
            }else{

                    val dataSet = doa.getRepository()

                    return LoadResult.Page(
                        data = dataSet,
                        prevKey = if (pagePosition == 1) null else pagePosition - 1,
                        nextKey = if (pagePosition == 100) null else pagePosition + 1
                    )

    }

    }
}