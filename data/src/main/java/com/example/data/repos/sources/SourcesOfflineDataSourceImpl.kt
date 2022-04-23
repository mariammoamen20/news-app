package com.example.data.repos.sources

import com.example.data.database.MyDataBase
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repo.SourcesOfflineDataSource

class SourcesOfflineDataSourceImpl(val myDatabase : MyDataBase) : SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItemDTO?>?) {
        myDatabase.sourcesDao().updateSources(sources)

    }

    override suspend fun getSourcesByCategoryId(category: String): List<SourcesItemDTO> {
   return myDatabase.sourcesDao().getSourcesByCategoryId(category)
    }


}