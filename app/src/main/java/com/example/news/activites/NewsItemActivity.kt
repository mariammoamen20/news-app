package com.example.news.activites

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.news.Constants
import com.example.news.R

class NewsItemActivity : AppCompatActivity() {
    lateinit var detilas_text: TextView
    lateinit var title_text: TextView
    lateinit var author_text: TextView
    lateinit var date_text: TextView
    lateinit var news_image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item)
        detilas_text = findViewById(R.id.news_text_detilas)
        title_text = findViewById(R.id.title_text_item)
        author_text = findViewById(R.id.author_text_item)
        date_text = findViewById(R.id.date_text_item)
        news_image = findViewById(R.id.news_image)

        val details_text_item: String? = intent.getStringExtra(Constants.EXTRA_ITEM_DETAILS)
        val title_text_item: String? = intent.getStringExtra(Constants.EXTRA_ITEM_TITLE)
        val author_text_item: String? = intent.getStringExtra(Constants.EXTRA_ITEM_AUTHOR)
        val date_text_item: String? = intent.getStringExtra(Constants.EXTRA_ITEM_DATE)
        val news_image_item : String?=intent.getStringExtra(Constants.EXTRA_ITEM_IMAGE)

        detilas_text.text = details_text_item
        title_text.text = title_text_item
        author_text.text = author_text_item
        date_text.text = date_text_item
        Glide.with(this)
            .load(news_image_item)
            .into(news_image)


    }
}