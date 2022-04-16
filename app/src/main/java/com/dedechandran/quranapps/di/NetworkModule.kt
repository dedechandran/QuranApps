package com.dedechandran.quranapps.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dedechandran.quranapps.BuildConfig
import com.dedechandran.quranapps.common.network.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .build()
    }

    @Provides
    fun provideClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}