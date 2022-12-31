package com.example.bs23.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bs23.data.model.DataConverter
import com.example.bs23.data.model.Item
import com.example.bs23.data.model.License
import com.example.bs23.data.model.Owner
import com.example.bs23.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(version = 3, entities = [Item::class, License::class, Owner::class])
@TypeConverters(DataConverter::class)
abstract class GitRepositoryDataBase :RoomDatabase () {


    abstract fun  getGitRepositoryDao():GitRepositoryDao
    abstract fun  getLicenseDao():LicenseDao
    abstract fun  getOwnerDao():OwnerDao

    class Callback @Inject constructor(
        private val database: Provider<GitRepositoryDataBase>
        ,@ApplicationScope private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback()

}