package com.example.news.repos.sources

import com.example.news.Constants
import com.example.news.api.WebServices
import com.example.domain.model.SourcesItem
//SourcesOnlineDataSourceImpl dependant on api
class SourcesOnlineDataSourceImpl(val webServices: WebServices) : SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            val res = webServices.getSources(Constants.API_KEY, category)
            return res.sources
        } catch (ex: Exception) {
            throw ex
        }
    }

}