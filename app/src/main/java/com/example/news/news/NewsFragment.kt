package com.example.news.news

import ArticlesItem
import NewsResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Constants
import com.example.news.R
import com.example.news.activites.NewsItemActivity
import com.example.news.adapter.NewAdapter
import com.example.news.api.ApiManger
import com.example.news.categories.Category
import com.example.news.model.SourceResponse
import com.example.news.model.SourcesItem
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class NewsFragment: Fragment() {

    companion object{
        fun getInstance(category: Category):NewsFragment{
            val news_fragment = NewsFragment()
            news_fragment.category = category
            return news_fragment
        }
    }
     var category= Category("",0,0,0)
    lateinit var tab_layout : TabLayout
    lateinit var progress_bar : ProgressBar
    lateinit var news_recycler_view: RecyclerView
    lateinit var news_adapter : NewAdapter
    lateinit var news_view_model : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize
        initViews()
        subscribesToLiveData()
        news_view_model.getNewsResources(category)


        //functions

    }

     fun subscribesToLiveData() {
        news_view_model.sources_livedata.observe(viewLifecycleOwner) {
            addSourcesToTabLayout(it)
        }
         news_view_model.news_live_data.observe(viewLifecycleOwner) {
             showNews(it)
         }
         news_view_model.progress_bar_live_data.observe(viewLifecycleOwner){
             progress_bar.isVisible = it
         }
         news_view_model.message_live_data.observe(viewLifecycleOwner){
             Toast.makeText(activity,it,Toast.LENGTH_SHORT).show()
         }
     }

     fun showNews(news_list: List<ArticlesItem?>?) {
         news_adapter.changData(news_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        news_view_model = ViewModelProvider(this).get(NewsViewModel::class.java)
    }
    private fun initViews() {
        tab_layout = requireView().findViewById(R.id.tab_layout)
        progress_bar =  requireView().findViewById(R.id.main_activity_progress_bar)
        news_recycler_view = requireView().findViewById(R.id.news_recycler_view)
        news_adapter = NewAdapter(null)
        news_recycler_view.adapter=news_adapter

        news_adapter.on_item_click_listeners = object :NewAdapter.OnItemClickListeners{
            override fun onItemClick(position: Int, atrical_item: ArticlesItem) {
                val intent = Intent(requireContext(), NewsItemActivity::class.java)
                intent.putExtra(Constants.EXTRA_ITEM_DETAILS,atrical_item.description)
                intent.putExtra(Constants.EXTRA_ITEM_TITLE,atrical_item.title)
                intent.putExtra(Constants.EXTRA_ITEM_AUTHOR,atrical_item.author)
                intent.putExtra(Constants.EXTRA_ITEM_DATE,atrical_item.publishedAt)
                intent.putExtra(Constants.EXTRA_ITEM_IMAGE,atrical_item.urlToImage)
                startActivity(intent)
            }

        }

    }


    private fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach{
            val sources_tab_layout = tab_layout.newTab()
            sources_tab_layout.setText(it?.name)
            sources_tab_layout.tag = it
            tab_layout.addTab(sources_tab_layout)
        }
        tab_layout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                news_view_model.getNewBySource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                news_view_model.getNewBySource(source)
            }

        })

        tab_layout.getTabAt(0)?.select()

    }


}