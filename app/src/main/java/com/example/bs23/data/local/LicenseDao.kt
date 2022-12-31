package com.example.bs23.data.local

import androidx.room.*
import com.example.bs23.data.model.Item
import com.example.bs23.data.model.License

@Dao
interface LicenseDao {
    @Query("SELECT * FROM tbLicense")
    fun getLicense() : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(license: License) : Long

    @Delete
    suspend fun delete(license: License)

    @Query("DELETE FROM tbLicense")
    suspend fun deleteAll()
}