package com.example.news.news

import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlImage")
fun loadImageView(image_view : ImageView,url : String){
    Glide.with(image_view).load(url).into(image_view)
}

@BindingAdapter("ImageResource")
fun setImageResource(image_view: ImageView , image_id : Int){
    image_view.setImageResource(image_id)
}
@BindingAdapter("CardBackground")
fun setBackgroundColor(card_view: CardView , card_color : Int){
   card_view.setCardBackgroundColor(
        ContextCompat.getColor(card_view.context, card_color)
    )
}

/*@BindingAdapter("searchBySource")
fun searchClick(edit_text:EditText , view_model:NewsViewModel){
    edit_text.setOnClickListener {
        view_model.artica_item_live_data
    }
}*/
