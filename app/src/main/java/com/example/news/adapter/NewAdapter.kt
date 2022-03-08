package com.example.news.adapter

import ArticlesItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.makeramen.roundedimageview.RoundedImageView

class NewAdapter(var list_item: List<ArticlesItem?>?) :
    RecyclerView.Adapter<NewAdapter.NewsViewHolder>() {
    class NewsViewHolder(item_view: View) : RecyclerView.ViewHolder(item_view) {
        val news_rounded_image: RoundedImageView = item_view.findViewById(R.id.news_image)
        val author_text: TextView = item_view.findViewById(R.id.author_text)
        val title_text: TextView = item_view.findViewById(R.id.title_text)
        val date_text: TextView = item_view.findViewById(R.id.date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view_holder = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view_holder)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list_item?.get(position)
        holder.author_text.text = item?.author
        holder.title_text.text = item?.title
        holder.date_text.text = item?.publishedAt
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.news_rounded_image)
        if(on_item_click_listeners != null){
           holder.itemView.setOnClickListener {
               on_item_click_listeners?.onItemClick(position,item!!)
           }
          }
    }

    override fun getItemCount(): Int {
        return list_item?.size ?: 0
    }
    var on_item_click_listeners : OnItemClickListeners?=null
    interface OnItemClickListeners{
        fun onItemClick(position: Int, atrical_item : ArticlesItem)
    }

    fun changData(data : List<ArticlesItem?>?) {
        list_item = data
        notifyDataSetChanged()
    }
}