package com.example.data.database

import androidx.room.*
import com.example.data.model.SourcesItem
import com.example.domain.model.SourcesItemDTO

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSourcesOffline():List<SourcesItemDTO?> //function to get sources

    @Query("select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category:String):List<SourcesItemDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources:List<SourcesItemDTO?>?)
}