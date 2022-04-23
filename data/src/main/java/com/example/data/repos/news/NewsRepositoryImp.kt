package com.example.data.repos.news

import com.example.domain.model.ArticlesItemDTO
import com.example.domain.repo.NewsOnlineDataSource
import com.example.domain.repo.NewsRepository

class NewsRepositoryImp(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        }catch (ex:Exception){
            throw ex
        }

    }
}