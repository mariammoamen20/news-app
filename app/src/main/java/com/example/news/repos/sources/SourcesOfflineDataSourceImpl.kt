package com.example.news.repos.sources

import com.example.news.database.MyDataBase
import com.example.domain.model.SourcesItem

class SourcesOfflineDataSourceImpl(val myDatabase :MyDataBase) : SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDatabase.sourcesDao().updateSources(sources)

    }

    override suspend fun getSourcesByCategoryId(category: String): List<SourcesItem> {
   return myDatabase.sourcesDao().getSourcesByCategoryId(category)
    }


}