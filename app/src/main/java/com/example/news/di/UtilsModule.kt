package com.example.news.di

import android.content.Context
import com.example.news.nh.NetworkHandler
import com.example.news.nh.NetworkHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context): NetworkHandler {
      return NetworkHandlerImpl(context)
    }
}