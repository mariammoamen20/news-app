package com.example.news.database

import androidx.room.*
import com.example.domain.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSourcesOffline():List<SourcesItem?> //function to get sources

    @Query("select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category:String):List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources:List<SourcesItem?>?)
}