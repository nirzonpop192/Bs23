package com.example.bs23.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bs23.R
import com.example.bs23.adapter.RepositoryPagingAdapter
import com.example.bs23.databinding.FragmentHomeBinding
import com.example.bs23.util.Constant
import com.example.bs23.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class HomeFragment : Fragment() {


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
        setAdapter()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadData("Android",Constant.SORT_BY,"asc",10)

        setObserver()
        setListener()

    }
    fun setObserver(){
        HomeViewModel.response.observe(viewLifecycleOwner){
            Log.e(TAG, "onResume:  size of dim ${it.items.size}", )

            if(it.items.isNotEmpty()){
                for (repositoryItem in it.items){
                    Log.e(TAG,"in loop")
                    viewModel.addRepository(repositoryItem)


                }
            }

        }

        viewModel.pagingDataList.observe(viewLifecycleOwner){
            Log.e(TAG, " observing ")
            mAdapter.submitData(lifecycle, it)
            Log.e(TAG, "onResume:  size of adapter ${mAdapter.snapshot().items.size}", )

        }
    }

    fun setAdapter (){
        mAdapter=RepositoryPagingAdapter()

        binding.rvRepositoryList.apply {
            this.layoutManager= LinearLayoutManager(requireContext())
            this.setHasFixedSize(true)
            this.adapter=mAdapter
        }
    }

    fun setListener(){
        binding.floatingActionButton.setOnClickListener {
            viewModel.deleteAllRepositories()
//            viewModel.loadData("Android","date","dsc",10)
            if (Constant.SORT_BY.equals(Constant.DATE)){
                binding.floatingActionButton.setImageResource(R.drawable.ic_date)
                Constant.SORT_BY = Constant.STARS
        }
            else{
                binding.floatingActionButton.setImageResource(R.drawable.ic_date)

                Constant.SORT_BY= Constant.DATE
            }


            val id = findNavController().currentDestination?.id
            findNavController().popBackStack(id!!,true)
            findNavController().navigate(id)
//            Handler().postDelayed( Runnable {
//               },1000)

        }
    }


}