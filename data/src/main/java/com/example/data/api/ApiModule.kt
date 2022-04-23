package com.example.data.api

import android.util.Log
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
object ApiModule {
    @Provides
    fun provideLoggingInterCeptor():HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger {
                HttpLoggingInterceptor.Logger { message -> Log.e("api", message) }
            }
        )
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return logging
    }
    @Provides
    fun provideOkHttpClient(logging:HttpLoggingInterceptor) : OkHttpClient{
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging).build()
        return client
    }
    @Provides
    fun provideGsonConverterFactory() :GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    const val BASE_URL = "https://newsapi.org/"
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}