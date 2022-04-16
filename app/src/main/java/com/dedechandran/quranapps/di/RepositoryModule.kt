package com.dedechandran.quranapps.di

import com.dedechandran.quranapps.data.QuranRepositoryImpl
import com.dedechandran.quranapps.domain.QuranRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindQuranRepository(quranRepositoryImpl: QuranRepositoryImpl): QuranRepository

}