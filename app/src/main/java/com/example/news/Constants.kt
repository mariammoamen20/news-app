package com.example.news

import com.example.news.nh.NetworkHandler

object Constants {
    const val API_KEY : String = "8606b4b05fdb4a99a3e944b172293a8a"
    const val BASE_URL = "https://newsapi.org/"
    const val EXTRA_ITEM_TITLE = "title"
    const val EXTRA_ITEM_DETAILS = "details"
    const val EXTRA_ITEM_AUTHOR = "author"
    const val EXTRA_ITEM_DATE = "date"
    const val EXTRA_ITEM_IMAGE = "image"
    lateinit var networkHandler: NetworkHandler



}