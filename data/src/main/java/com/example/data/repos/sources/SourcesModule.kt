package com.example.data.repos.sources

import com.example.data.api.WebServices
import com.example.data.database.MyDataBase
import com.example.domain.utlis.NetworkHandler
import com.example.domain.repo.SourcesOfflineDataSource
import com.example.domain.repo.SourcesOnlineDataSource
import com.example.domain.repo.SourcesRepository
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