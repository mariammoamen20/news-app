package com.example.news.repos.news

import com.example.news.api.ApiManger
import com.example.news.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices): NewsOnlineDataSource {
        return NewsOnlineDataSourceImp(webServices)
    }

    @Provides
    fun provideNewRepository(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImp(newsOnlineDataSource)
    }

}