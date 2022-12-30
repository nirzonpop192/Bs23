package com.example.bs23.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bs23.adapter.RepositoryPagingAdapter
import com.example.bs23.databinding.FragmentHomeBinding
import com.example.bs23.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: RepositoryPagingAdapter

    companion object{
         val TAG= HomeFragment::class.java.name
    }
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        if(NetworkManager.isNetConnectionAvailable(requireContext()))
//            viewModel.fetRepositoryApi("Android","stars","desc",10,1)

        mAdapter=RepositoryPagingAdapter()

        binding.rvRepositoryList.apply {
            this.layoutManager= LinearLayoutManager(requireContext())
            this.setHasFixedSize(true)
            this.adapter=mAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadData("Android","stars","desc",10)

    viewModel.pagingDataList.observe(viewLifecycleOwner){
        Log.e(TAG, " observing ")
        mAdapter.submitData(lifecycle, it)
        Log.e(TAG, "onResume:  size of adapter ${mAdapter.snapshot().items.size}", )

    }
        HomeViewModel.response.observe(viewLifecycleOwner){
            Log.e(TAG, "onResume:  size of dim ${it.items.size}", )

            if(it.items.isNotEmpty()){
                for (repositoryItem in it.items){
                    Log.e(TAG,"in loop")
                    viewModel.addRepository(repositoryItem)


                }
            }

        }
    //        viewModel.responseLiveData.observe(viewLifecycleOwner) {
//            Log.e(TAG, it.toString())
//            Log.e(TAG,"in over")
//            if(it.items.isNotEmpty()){
//                for (repositoryItem in it.items){
//                    Log.e(TAG,"in loop")
//                    viewModel.addRepository(repositoryItem)
//
//
//                }
//            }
//        }
    }


}