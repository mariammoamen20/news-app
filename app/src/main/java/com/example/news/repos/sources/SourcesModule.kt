package com.example.news.repos.sources

import com.example.news.nh.NetworkHandler
import com.example.news.api.ApiManger
import com.example.news.api.WebServices
import com.example.news.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {
    @Provides
    fun provideOnlineDataSource(webServices: WebServices): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }

    @Provides
    fun provideOfflineDataSource(dataBase: MyDataBase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(dataBase)
    }

    @Provides
    @Singleton
    fun provideDatabase(): MyDataBase {
        return MyDataBase.getInstance()
    }
    @Provides
    fun provideSourcesRepo(
        sourcesOfflineDataSource: SourcesOfflineDataSource,
        sourcesOnlineDataSource: SourcesOnlineDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(
            sourcesOfflineDataSource,
            sourcesOnlineDataSource,
            networkHandler
        )
    }

}