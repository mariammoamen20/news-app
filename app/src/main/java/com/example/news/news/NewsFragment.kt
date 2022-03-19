package com.example.news.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.Constants
import com.example.news.R
import com.example.news.categories.Category
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.model.ArticlesItem
import com.example.news.model.SourcesItem
import com.example.news.newsitem.NewsItemActivity
import com.google.android.material.tabs.TabLayout


class NewsFragment : Fragment() {
    lateinit var fragment_news_databinding: FragmentNewsBinding
    lateinit var news_adapter: NewAdapter
    lateinit var news_view_model: NewsViewModel

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val news_fragment = NewsFragment()
            news_fragment.category = category
            return news_fragment
        }
    }

    var category = Category("", 0, 0, 0)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment_news_databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return fragment_news_databinding.root

        //return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize
        initViews()
        subscribesToLiveData()
        news_view_model.getNewsResources(category)
    }

    fun subscribesToLiveData() {
        news_view_model.sources_livedata.observe(viewLifecycleOwner) {
            addSourcesToTabLayout(it)
        }
        news_view_model.news_live_data.observe(viewLifecycleOwner) {
            showNews(it)
        }
        news_view_model.progress_bar_live_data.observe(viewLifecycleOwner) {
            fragment_news_databinding.mainActivityProgressBar.isVisible = it
        }
        news_view_model.message_live_data.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
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
        /*tab_layout = requireView().findViewById(R.id.tab_layout)
        progress_bar =  requireView().findViewById(R.id.main_activity_progress_bar)
        news_recycler_view = requireView().findViewById(R.id.news_recycler_view)*/
        news_adapter = NewAdapter(null)
        fragment_news_databinding.newsRecyclerView.adapter = news_adapter

        news_adapter.on_item_click_listeners = object : NewAdapter.OnItemClickListeners {
            override fun onItemClick(position: Int, atrical_item: ArticlesItem) {
                val intent = Intent(requireContext(), NewsItemActivity::class.java)
                intent.putExtra(Constants.EXTRA_ITEM_DETAILS, atrical_item.description)
                intent.putExtra(Constants.EXTRA_ITEM_TITLE, atrical_item.title)
                intent.putExtra(Constants.EXTRA_ITEM_AUTHOR, atrical_item.author)
                intent.putExtra(Constants.EXTRA_ITEM_DATE, atrical_item.publishedAt)
                intent.putExtra(Constants.EXTRA_ITEM_IMAGE, atrical_item.urlToImage)
                startActivity(intent)
            }

        }

    }

    private fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {
            val sources_tab_layout = fragment_news_databinding.tabLayout.newTab()
            sources_tab_layout.setText(it?.name)
            sources_tab_layout.tag = it
            fragment_news_databinding.tabLayout.addTab(sources_tab_layout)
        }
        fragment_news_databinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
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

        fragment_news_databinding.tabLayout.getTabAt(0)?.select()

    }


}