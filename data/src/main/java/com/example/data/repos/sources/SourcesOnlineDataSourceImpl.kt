package com.example.data.repos.sources

import com.example.data.api.WebServices
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repo.SourcesOnlineDataSource

//SourcesOnlineDataSourceImpl dependant on api
class SourcesOnlineDataSourceImpl(val webServices: WebServices) : SourcesOnlineDataSource {
     val API_KEY : String = "8606b4b05fdb4a99a3e944b172293a8a"
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
        try {
            val res = webServices.getSources(API_KEY, category)
            return res.toSourcesResponseDTO().sources
        } catch (ex: Exception) {
            throw ex
        }
    }

}