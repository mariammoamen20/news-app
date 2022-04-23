package com.example.data.repos.sources

import com.example.domain.utlis.NetworkHandler
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repo.SourcesOfflineDataSource
import com.example.domain.repo.SourcesOnlineDataSource
import com.example.domain.repo.SourcesRepository

class SourcesRepositoryImpl(
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val networkHandler: NetworkHandler
    ) :
    SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
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