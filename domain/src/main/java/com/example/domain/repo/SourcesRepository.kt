package com.example.domain.repo

import com.example.domain.model.SourcesItemDTO

interface SourcesRepository {
    suspend fun getSources(category: String):List<SourcesItemDTO?>?
}
interface SourcesOnlineDataSource {
    suspend fun getSources(category: String):List<SourcesItemDTO?>?
}
interface SourcesOfflineDataSource{
    suspend fun updateSources(sources:List<SourcesItemDTO?>?)
    suspend fun getSourcesByCategoryId(category: String):List<SourcesItemDTO>
}