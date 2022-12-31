package com.example.bs23.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ExtendedMethod {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun formatDateTime(rawData:String?):String?{
            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val output = SimpleDateFormat("MM-dd-yyyy HH:mm")

            if (rawData==null)
                return ""

            var d: Date? = null
            try {
                d = input.parse(rawData)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val formatted: String? = d?.let { output.format(it) }
            Log.i("DATE", "" + formatted)
            return formatted
        }
    }
}