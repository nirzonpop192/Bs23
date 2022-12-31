package com.example.bs23.view_model

interface ServiceInvokinListerner {
     fun invokeApi(query: String , sort: String, order: String, per_page: Int)
}