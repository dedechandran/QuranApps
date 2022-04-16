package com.dedechandran.quranapps.di

import com.dedechandran.quranapps.data.local.LocalDataSource
import com.dedechandran.quranapps.data.local.LocalDataSourceImpl
import com.dedechandran.quranapps.data.remote.RemoteDataSource
import com.dedechandran.quranapps.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}