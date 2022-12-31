package com.example.bs23.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bs23.R
import com.example.bs23.util.Constant
import com.example.bs23.util.NetworkManager
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Constant.SORT_BY= Constant.DATE
        Constant.IS_NETWORK_AVAILABLE=NetworkManager.isNetConnectionAvailable(this)
    }
}