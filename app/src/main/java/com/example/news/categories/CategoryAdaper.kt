package com.example.news.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.google.android.material.card.MaterialCardView

class CategoryAdaper(val category_list: List<Category>) :
    RecyclerView.Adapter<CategoryAdaper.CategoryViewHolder>() {
    class CategoryViewHolder(item_view: View) : RecyclerView.ViewHolder(item_view) {
        val category_title: TextView = item_view.findViewById(R.id.category_text)
        val category_image: ImageView = item_view.findViewById(R.id.category_image)
        val material_card_view: MaterialCardView = item_view.findViewById(R.id.material_card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == LEFT_SIDE_CATEGORY) R.layout.left_side_category
            else R.layout.right_side_category,
            parent,
            false
        )
        return CategoryViewHolder(view)
    }

    val LEFT_SIDE_CATEGORY = 10;
    val RIGHT_SIDE_CATEGORY = 20;
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) LEFT_SIDE_CATEGORY else RIGHT_SIDE_CATEGORY
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = category_list.get(position)
        holder.category_title.setText(item.title_id)
        holder.category_image.setImageResource(item.image_resource)
        holder.material_card_view.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context,item.card_background))

        if(on_item_click_listeners!=null){
            holder.itemView.setOnClickListener {
                on_item_click_listeners?.onItemClick(position,item)
            }
        }
    }

    override fun getItemCount(): Int {
        return category_list.size

    }
    var on_item_click_listeners : OnItemClickListeners?=null
    interface OnItemClickListeners{
        fun onItemClick(position : Int , category : Category)
    }

}