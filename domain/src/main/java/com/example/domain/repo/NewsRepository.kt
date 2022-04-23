package com.example.domain.repo

import com.example.domain.model.ArticlesItemDTO


interface NewsRepository {
    suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>?
}

interface NewsOnlineDataSource {
    suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItemDTO?>?

}