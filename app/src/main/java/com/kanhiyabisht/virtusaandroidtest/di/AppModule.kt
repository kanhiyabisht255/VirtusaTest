package com.kanhiyabisht.virtusaandroidtest.di

import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.BASE_URL
import com.kanhiyabisht.virtusaandroidtest.data.remote.FreeGameApi
import com.kanhiyabisht.virtusaandroidtest.data.repository.FreeGameRepositoryImpl
import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideRemoteInstance(retrofit: Retrofit): FreeGameApi =
        retrofit.create(FreeGameApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteRepository(freeGameApi: FreeGameApi): FreeGameRepository {
        return FreeGameRepositoryImpl(freeGameApi)
    }


}