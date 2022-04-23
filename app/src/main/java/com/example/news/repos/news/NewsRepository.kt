package com.example.news.repos.news

import com.example.domain.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<ArticlesItem?>?
}

interface NewsOnlineDataSource {
    suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>?

}