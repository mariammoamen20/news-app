package com.example.news.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.LeftSideCategoryBinding
import com.example.news.databinding.RightSideCategoryBinding
import com.google.android.material.card.MaterialCardView

class CategoryAdaper(val category_list: List<Category>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CategoryLeftViewHolder(var category_left_bindind: LeftSideCategoryBinding) : RecyclerView.ViewHolder(category_left_bindind.root) {
       /* val category_title: TextView = item_view.findViewById(R.id.category_text)
        val category_image: ImageView = item_view.findViewById(R.id.category_image)
        val material_card_view: MaterialCardView = item_view.findViewById(R.id.material_card_view)*/
       fun bind(left_item : Category){
           category_left_bindind.leftItem = left_item
           category_left_bindind.invalidateAll()
       }
    }
    class CategoryRighttViewHolder(var category_right_bindind: RightSideCategoryBinding) : RecyclerView.ViewHolder(category_right_bindind.root) {
        /* val category_title: TextView = item_view.findViewById(R.id.category_text)
         val category_image: ImageView = item_view.findViewById(R.id.category_image)
         val material_card_view: MaterialCardView = item_view.findViewById(R.id.material_card_view)*/
        fun bind(right_item : Category){
            category_right_bindind.rightItem = right_item
            category_right_bindind.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /* val view = LayoutInflater.from(parent.context).inflate(
             if (viewType == LEFT_SIDE_CATEGORY) R.layout.left_side_category
             else R.layout.right_side_category,
             parent,
             false
         )
         return CategoryViewHolder(view)*/
        if (viewType == LEFT_SIDE_CATEGORY) {
            val left_side_bindind: LeftSideCategoryBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.left_side_category, parent, false
                )
            return CategoryLeftViewHolder(left_side_bindind)
        } else {
            val right_side_binding: RightSideCategoryBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.right_side_category,
                    parent,
                    false
                )
            return CategoryRighttViewHolder(right_side_binding)
        }
    }

    val LEFT_SIDE_CATEGORY = 10;
    val RIGHT_SIDE_CATEGORY = 20;
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) LEFT_SIDE_CATEGORY else RIGHT_SIDE_CATEGORY
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = category_list.get(position)
        if(getItemViewType(position) == LEFT_SIDE_CATEGORY){
            (holder as CategoryLeftViewHolder).bind(item)
        }else if(getItemViewType(position) == RIGHT_SIDE_CATEGORY){
            (holder as CategoryRighttViewHolder).bind(item )
        }
       /* holder.category_title.setText(item.title_id)
        holder.category_image.setImageResource(item.image_resource)
        holder.material_card_view.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, item.card_background)
        )*/

        if (on_item_click_listeners != null) {
            holder.itemView.setOnClickListener {
                on_item_click_listeners?.onItemClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return category_list.size

    }

    var on_item_click_listeners: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onItemClick(position: Int, category: Category)
    }

}