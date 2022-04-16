package com.dedechandran.quranapps.di

import com.dedechandran.quranapps.common.DispatcherProvider
import com.dedechandran.quranapps.common.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcher(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}