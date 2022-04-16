package com.dedechandran.quranapps.di

import com.dedechandran.quranapps.data.remote.QuranService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideQuranService(retrofit: Retrofit): QuranService {
        return retrofit.create(QuranService::class.java)
    }
}