package com.example.bs23.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bs23.data.model.Item
import com.example.bs23.data.model.License
import com.example.bs23.data.model.Owner

@Dao
interface OwnerDao {
    @Query("SELECT * FROM tbOwner")
    fun getLicense() : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(owner: Owner) : Long

    @Delete
    suspend fun delete(owner: Owner)

    @Query("DELETE FROM tbOwner")
    suspend fun deleteAll()
}