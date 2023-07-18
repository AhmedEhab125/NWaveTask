package com.example.nwavetask.di

import com.example.nwavetask.DataBase.ILocalDataSource
import com.example.nwavetask.DataBase.LocalDataSource
import com.example.nwavetask.repository.IRepository
import com.example.nwavetask.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(repository : Repository) : IRepository
    @Binds
    @Singleton
    abstract fun localDataSourceInjection(
        localSource: LocalDataSource
    ): ILocalDataSource
}