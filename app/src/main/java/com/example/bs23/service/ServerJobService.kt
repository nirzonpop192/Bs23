package com.example.bs23.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.bs23.ui.HomeFragment
import com.example.bs23.util.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class ServerJobService: JobService() {
    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var jobCancelled: Boolean = false

    private val scope = CoroutineScope(Dispatchers.IO)

//     val repository :ServiceInvokinListerner
     val viewModel= HomeFragment.viewModel
    companion object {
        val TAG: String = ServerJobService.javaClass.name
        var delay = 0
        const val miliSecon = 60 * 1000
    }

    override fun onStartJob(params: JobParameters?): Boolean {


        Log.e(TAG, "onStartJob")


        val minetes = 30
        delay = minetes * miliSecon

        Log.e(TAG, "in Job  timer $minetes")
        Log.e(TAG, "in Job  delay $delay")

        handler.postDelayed(Runnable {

            handler.postDelayed(runnable!!, delay.toLong())

            Toast.makeText(this, "fetching data from server ", Toast.LENGTH_LONG).show()
            Log.e(TAG, "network call ")
            scope.launch {
                withContext(Dispatchers.Main) {
                     viewModel.invokeApi("Android",Constant.SORT_BY,"asc",10)
                }

            }

        }.also { runnable = it }, delay.toLong())


        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG, "onStopJob")
        handler.removeCallbacks(runnable!!)
        jobCancelled = true
        return true
    }
}