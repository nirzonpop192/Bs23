package com.example.bs23

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bs23.databinding.FragmentHomeBinding
import com.example.bs23.view_model.HomeViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object{
         val TAG=HomeFragment::class.java.name
    }
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        viewModel.fetRepositoryApi("Android","stars","desc",10,1)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.response.observe(viewLifecycleOwner) {
            Log.e(TAG, it.toString())


        }
    }


}