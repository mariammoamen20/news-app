package com.example.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun sourcesDao(): SourcesDao

    companion object {
        var database: MyDataBase? = null
        const val DATABADE_NAME = "newsdatabase"
        fun init(context: Context) {
            if (database == null) {
                //initialize
                database = Room.databaseBuilder(context, MyDataBase::class.java, DATABADE_NAME)
                    .fallbackToDestructiveMigration().build()
            }
        }
        fun getInstance() : MyDataBase{
            return database!!
        }
    }
}