package com.example.news.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
//      technology
class CategoriesFragment : Fragment(){
    lateinit var category_recycler_view : RecyclerView
    val categories = listOf(
        Category("sports",R.drawable.ball,R.string.sports,R.color.red),
        Category("technology",R.drawable.politics,R.string.technology,R.color.blue),
        Category("health",R.drawable.health,R.string.health,R.color.pink),
        Category("business",R.drawable.bussines,R.string.business,R.color.brown),
        Category("general",R.drawable.environment,R.string.general,R.color.baby_blue),
        Category("science",R.drawable.science,R.string.science,R.color.yellow),

        )
    val category_adpater = CategoryAdaper(categories)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category_recycler_view = requireView().findViewById(R.id.recycler_view)
        category_recycler_view.adapter = category_adpater
        category_adpater.on_item_click_listeners = object : CategoryAdaper.OnItemClickListeners{
            override fun onItemClick(position: Int, category: Category) {
               on_category_click_listeners?.onCategoryClick(category)
            }
        }
    }
    var on_category_click_listeners: OnCategoryClickListeners?=null
    interface OnCategoryClickListeners{
        fun onCategoryClick(category: Category)
    }
}