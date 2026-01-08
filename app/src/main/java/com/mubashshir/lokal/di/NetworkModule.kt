package com.mubashshir.lokal.di

import com.mubashshir.lokal.data.remote.LokalApiService
import com.mubashshir.lokal.data.repository.LokalRepository
import com.mubashshir.lokal.data.repository.LokalRepositoryImpl
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
object NetworkModule {

    private const val BASE_URL = "https://saavn.sumit.co/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLokalApiService(retrofit: Retrofit): LokalApiService {
        return retrofit.create(LokalApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLokalRepository(lokalApiService: LokalApiService): LokalRepository {
        return LokalRepositoryImpl(lokalApiService)
    }
}
