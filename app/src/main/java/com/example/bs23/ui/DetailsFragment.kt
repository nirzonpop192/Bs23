package com.example.bs23.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.bs23.R
import com.example.bs23.data.model.Item
import com.example.bs23.databinding.FragmentDetailsBinding
import com.example.bs23.databinding.FragmentHomeBinding
import com.example.bs23.view_model.HomeViewModel


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private  var item : Item?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

         item =arguments?.getParcelable<Item>("repository")
        Log.e("dim", "onCreateView: ${item?.name}", )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.tvTitle.text=item?.name
        binding.tvDescription.text=item?.description
    }


}