package com.example.news.repos.news

import com.example.domain.model.ArticlesItem

class NewsRepositoryImp(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        }catch (ex:Exception){
            throw ex
        }

    }
}