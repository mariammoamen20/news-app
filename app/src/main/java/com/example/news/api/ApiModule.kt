package com.example.news.api

import android.util.Log
import com.example.news.Constants
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
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}