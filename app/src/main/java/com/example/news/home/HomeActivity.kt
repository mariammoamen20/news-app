package com.example.news.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.news.R
import com.example.news.categories.CategoriesFragment
import com.example.news.categories.Category
import com.example.news.news.NewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var drawer_layout: DrawerLayout
    lateinit var image_drawe: ImageView
    lateinit var categerioes_icon: View
//    lateinit var toolbar_text : TextView
    val categories_fragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        pushFragment(categories_fragment)
        categories_fragment.on_category_click_listeners = object :CategoriesFragment.OnCategoryClickListeners{
            override fun onCategoryClick(category: Category) {
                pushFragment(NewsFragment.getInstance(category),true)
                drawer_layout.close()
            }

        }

    }

    private fun initViews() {

        drawer_layout = findViewById(R.id.drawer_layout)
        image_drawe = findViewById(R.id.toolbar_image)
        //toolbar_text = findViewById(R.id.toolbar_title)
        categerioes_icon = findViewById(R.id.categories_linear_layout)
        //toolbar_text.text = "Category"

        clickListeners()
    }
    private fun clickListeners() {
        image_drawe.setOnClickListener {
            drawer_layout.open()
        }

        categerioes_icon.setOnClickListener {
             pushFragment(categories_fragment)
            //toolbar_text.text = "Category"
            drawer_layout.close()

        }


    }

    fun pushFragment(fragment : Fragment , add_to_back_stack: Boolean = false){
       val transaction_fragment = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
        if(add_to_back_stack){
            transaction_fragment.addToBackStack("")
        }
        transaction_fragment.commit()
    }
}