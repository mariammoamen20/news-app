package com.example.news.repos.news

import com.example.news.Constants
import com.example.news.api.WebServices
import com.example.domain.model.ArticlesItem

class NewsOnlineDataSourceImp(val webServices: WebServices) : NewsOnlineDataSource {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result =webServices.getNewsResources(Constants.API_KEY,sourceId)
            return result.articles
        }catch (ex:Exception){
            throw ex
        }
    }
}