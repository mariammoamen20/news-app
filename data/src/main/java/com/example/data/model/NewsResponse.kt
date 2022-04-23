package com.example.data.model

import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.NewsResponseDTO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
){
	fun toNewsResponseDTO():NewsResponseDTO{
		val json= Gson().toJson(this)
		return Gson().fromJson(json,NewsResponseDTO::class.java)
	}
}

data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
{
	fun toArticlesItemDTO():ArticlesItemDTO{
		val jsonString = Gson().toJson(this)
		return Gson().fromJson(jsonString,ArticlesItemDTO::class.java)
	}
}
data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

