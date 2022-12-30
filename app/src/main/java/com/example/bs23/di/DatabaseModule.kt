package com.example.bs23.di

import android.app.Application
import androidx.room.Room
import com.example.bs23.data.local.GitRepositoryDao
import com.example.bs23.data.local.GitRepositoryDataBase
import com.example.bs23.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

        companion object{
            private const val DATA_BASE_NAME=Constant.DATA_BASE_NAME
        }

    @Provides
    @Singleton
    fun provideDatabase(application: Application,callback: GitRepositoryDataBase.Callback):GitRepositoryDataBase{
        return Room.databaseBuilder(application,GitRepositoryDataBase::class.java,DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideGitRepositoryDoa(database:GitRepositoryDataBase):GitRepositoryDao{
        return database.getGitRepositoryDao()
    }
}