package com.example.bs23.view_model
import com.example.bs23.data.model.GitHubResponse
import com.example.bs23.repository.HomeRepository
import com.example.bs23.util.Constant

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test

internal class HomeViewModelTest {
    private val repository: HomeRepository = mockk{
        coEvery {  fetchRepositoryApi( any(),any(),any(),any())} returns mockk()
    }
    private val  viewModel:HomeViewModel= HomeViewModel(repository)

    @Test
    fun `when fetching repository content state is show`(){
        viewModel.loadData("Android", Constant.SORT_BY,"asc",10)
        assertThat(HomeViewModel.response.value is GitHubResponse)
    }

}