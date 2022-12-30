package com.example.bs23.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.data.model.Item
import com.example.bs23.data.remote.GitHubApi
import com.example.bs23.view_model.HomeViewModel

class RepoPagingSource(private val api: GitHubApi, private val query: String ,
                       private val  sort: String,private val  order: String,
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

            val  pagePosition= params.key ?: 1
            val  response=api.getRepository(query , sort, order, per_page,pagePosition)

            return LoadResult.Page(
                data = response.items,
                prevKey =  if(pagePosition==1 ) null else pagePosition-1,
                nextKey =  if(pagePosition==100) null else pagePosition+1
            )

    }
}