package com.example.bs23.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bs23.adapter.RepositoryPagingAdapter
import com.example.bs23.databinding.FragmentHomeBinding
import com.example.bs23.service.ServerJobService
import com.example.bs23.util.Constant
import com.example.bs23.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val JOB_ID: Int=123
    lateinit var mAdapter: RepositoryPagingAdapter
    private lateinit var jobScheduler: JobScheduler
   // val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    companion object{
         val TAG= HomeFragment::class.java.name
        lateinit var viewModel: HomeViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setAdapter()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (Constant.SORT_BY.equals(Constant.DATE)) binding.btnSort.text=" sort by star"

        else binding.btnSort.text=" sort by date"


        viewModel.loadData("Android",Constant.SORT_BY,"asc",10)

        setObserver()
        setListener()
        startJobScheduler()
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
        binding.btnSort.setOnClickListener {
            viewModel.deleteAllRepositories()

            if (Constant.SORT_BY.equals(Constant.DATE)) Constant.SORT_BY = Constant.STARS

            else Constant.SORT_BY= Constant.DATE

            val id = findNavController().currentDestination?.id
            findNavController().popBackStack(id!!,true)
            findNavController().navigate(id)
        }

    }

    fun  startJobScheduler(){
        val  componentName =  ComponentName(requireContext(), ServerJobService::class.java)

        val  info: JobInfo = JobInfo.Builder(JOB_ID,componentName)
            .setMinimumLatency(1)
            .setRequiresCharging(false)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            //.setPeriodic(10*1000)
            .setOverrideDeadline(1*60*1000)
            .build()

        jobScheduler= requireContext().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val resultCode=jobScheduler.schedule(info)

        if (resultCode==JobScheduler.RESULT_SUCCESS) Log.e(TAG,"job scheduler success")

        else if(resultCode==JobScheduler.RESULT_FAILURE) Log.e(TAG,"job scheduler failed")

        else Log.e(TAG,"job scheduler")
    }

    override fun onPause() {
        super.onPause()
        jobScheduler.cancel(JOB_ID)
    }


}