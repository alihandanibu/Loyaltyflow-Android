package com.example.loyalityflow.di

import com.example.loyalityflow.data.remote.api.LoyaltyCardApiService
import com.example.loyalityflow.data.remote.repository.LoyaltyCardRepository
import com.example.loyalityflow.data.remote.repository.LoyaltyCardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideLoyaltyCardApiService(
            retrofit: Retrofit
        ): LoyaltyCardApiService {
            return retrofit.create(LoyaltyCardApiService::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindLoyaltyCardRepository(
        loyaltyCardRepositoryImpl: LoyaltyCardRepositoryImpl
    ): LoyaltyCardRepository
}