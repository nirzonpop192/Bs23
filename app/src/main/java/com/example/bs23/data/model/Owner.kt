package com.example.bs23.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbOwner")
@Parcelize
data class Owner(
    val avatar_url: String?,
    @ColumnInfo(name = "owner_events_url")
    val events_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    @ColumnInfo(name = "owner_html_url")
    val html_url: String?,
    @PrimaryKey
    @ColumnInfo(name = "owner_id")
    val id: Int,
    val login: String?,
    @ColumnInfo(name = "owner_node_id")
    val node_id: String?,
    val organizations_url: String?,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val type: String?,
    @ColumnInfo(name = "owner_url")
    val url: String
):Parcelable{
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        "",
        "",
        false,
        "",
        "",
        "",
        ""
    )
}