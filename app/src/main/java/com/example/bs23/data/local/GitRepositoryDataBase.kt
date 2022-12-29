package com.example.bs23.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bs23.data.model.Item
import com.example.bs23.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(version = 2, entities = [Item::class/*,License::class,Owner::class*/])
abstract class GitRepositoryDataBase :RoomDatabase () {


    abstract fun  getGitRepositoryDao():GitRepositoryDao

    class Callback @Inject constructor(
        private val database: Provider<GitRepositoryDataBase>
        ,@ApplicationScope private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback()

}