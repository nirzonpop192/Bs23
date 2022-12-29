package com.example.bs23.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "License")
data class License(
    val key: String,
    val name: String,
  //  @PrimaryKey
    val node_id: String,
    val spdx_id: String,
    val url: String
)