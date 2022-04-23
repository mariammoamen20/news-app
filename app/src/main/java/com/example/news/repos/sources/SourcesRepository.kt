package com.example.news.repos.sources

import com.example.domain.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(category: String):List<SourcesItem?>?
}
interface SourcesOnlineDataSource {
    suspend fun getSources(category: String):List<SourcesItem?>?
}
interface SourcesOfflineDataSource{
    suspend fun updateSources(sources:List<SourcesItem?>?)
    suspend fun getSourcesByCategoryId(category: String):List<SourcesItem>
}