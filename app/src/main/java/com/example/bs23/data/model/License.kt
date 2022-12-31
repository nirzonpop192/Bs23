package com.example.bs23.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// todo add table and convert type
@Entity(tableName = "tbLicense")
@Parcelize
data class License(
    val key: String?,
    @ColumnInfo(name = "license_name")
    val name: String?,
    @PrimaryKey
    @ColumnInfo(name = "license_node_id")
    val node_id: String,
    val spdx_id: String?,
    @ColumnInfo(name = "license_url")
    val url: String?
):Parcelable{
    constructor(): this(
        "",
        "",
        "",
        "",
        ""
    )
}