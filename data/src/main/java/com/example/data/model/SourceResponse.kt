package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.SourceResponseDTO
import com.example.domain.model.SourcesItemDTO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SourceResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
){
    fun toSourcesResponseDTO():SourceResponseDTO{
        val json = Gson().toJson(this)
        return Gson().fromJson(json,SourceResponseDTO::class.java)
    }
}
@Entity
data class SourcesItem(
    @ColumnInfo
    @field:SerializedName("country")
    val country: String? = null,

    @ColumnInfo
    @field:SerializedName("name")
    val name: String? = null,

    @ColumnInfo
    @field:SerializedName("description")
    val description: String? = null,

    @ColumnInfo
    @field:SerializedName("language")
    val language: String? = null,

    @ColumnInfo
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @ColumnInfo
    @field:SerializedName("category")
    val category: String? = null,

    @ColumnInfo
    @field:SerializedName("url")
    val url: String? = null
){
    fun toSourceItemDTO():SourcesItemDTO{
        val jsonString = Gson().toJson(this)
        return Gson().fromJson(jsonString,SourcesItemDTO::class.java)
    }
}
