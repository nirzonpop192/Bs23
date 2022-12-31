package com.example.bs23.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// todo add table and convert type
//@Entity(tableName = "License")
@Parcelize
data class License(
    val key: String,
    val name: String,
  //  @PrimaryKey
    val node_id: String,
    val spdx_id: String,
    val url: String
):Parcelable