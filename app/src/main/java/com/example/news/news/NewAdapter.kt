package com.example.news.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.NewsItemBinding
import com.example.domain.model.ArticlesItem

class NewAdapter() :
    RecyclerView.Adapter<NewAdapter.NewsViewHolder>() {
    var list_item= listOf<ArticlesItem?>()
    class NewsViewHolder(val item_binding:NewsItemBinding) : RecyclerView.ViewHolder(item_binding.root) {
        fun bind(artical_item : ArticlesItem? ){
             item_binding.item = artical_item
             item_binding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       /* val view_holder = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view_holder)*/
      val view_binding : NewsItemBinding =
          DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.news_item,parent,false)
          return NewsViewHolder(view_binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list_item?.get(position)
        holder.bind(item)
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

    fun changData(data : List<ArticlesItem?>) {
        list_item = data
        notifyDataSetChanged()
    }

}