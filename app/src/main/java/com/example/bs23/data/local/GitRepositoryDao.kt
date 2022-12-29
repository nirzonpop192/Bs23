package com.example.bs23.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bs23.data.model.Item

@Dao
interface GitRepositoryDao {
    @Query("SELECT * FROM tbRepository")
    fun getRepository() : LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryItem: Item) : Long

    @Delete
    suspend fun delete(repositoryItem: Item)

//    @Query("DELETE FROM tbRepository")
//    suspend fun deleteAllArticles()
}