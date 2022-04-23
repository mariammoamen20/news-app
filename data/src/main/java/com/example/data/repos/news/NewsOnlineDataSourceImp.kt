package com.example.data.repos.news

import com.example.data.api.WebServices
import com.example.domain.model.ArticlesItemDTO
import com.example.domain.repo.NewsOnlineDataSource

class NewsOnlineDataSourceImp(val webServices: WebServices) : NewsOnlineDataSource {
     val API_KEY : String = "8606b4b05fdb4a99a3e944b172293a8a"
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItemDTO?>? {
        try {
            val result =webServices.getNewsResources(API_KEY,sourceId)
            return result.toNewsResponseDTO().articles
        }catch (ex:Exception){
            throw ex
        }
    }
}