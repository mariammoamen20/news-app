package com.example.data.repos.news

import com.example.data.api.WebServices
import com.example.domain.repo.NewsOnlineDataSource
import com.example.domain.repo.NewsRepository
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