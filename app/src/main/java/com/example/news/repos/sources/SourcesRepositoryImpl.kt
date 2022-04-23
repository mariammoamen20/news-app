package com.example.news.repos.sources

import com.example.news.nh.NetworkHandler
import com.example.domain.model.SourcesItem

class SourcesRepositoryImpl(
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val networkHandler: NetworkHandler
    ) :
    SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if (networkHandler.isOnline()){
                return sourcesOnlineDataSource.getSources(category)

                sourcesOfflineDataSource.updateSources(sourcesOnlineDataSource.getSources(category)) //cash data

            }

            return sourcesOfflineDataSource.getSourcesByCategoryId(category);

        } catch (ex: Exception) {
            return sourcesOfflineDataSource.getSourcesByCategoryId(category) //increase couldn't retrieve data from network
        }

    }
}