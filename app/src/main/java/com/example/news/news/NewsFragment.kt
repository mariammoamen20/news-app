package com.example.news.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.Constants
import com.example.news.R
import com.example.news.categories.Category
import com.example.news.databinding.FragmentNewsBinding
import com.example.domain.model.ArticlesItem
import com.example.domain.model.SourcesItem
import com.example.news.newsitem.NewsItemActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var fragmentNewsDatabinding: FragmentNewsBinding
    lateinit var newsViewModel: NewsViewModel

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val news_fragment = NewsFragment()
            news_fragment.category = category
            return news_fragment
        }
    }

    var category = Category("", 0, 0, 0)
    @Inject lateinit var newsAdapter : NewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsDatabinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return fragmentNewsDatabinding.root

        //return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize
        initViews()
        subscribesToLiveData()
        newsViewModel.getNewsResources(category)
    }

    fun subscribesToLiveData() {
        newsViewModel.sourcesLivedata.observe(viewLifecycleOwner) {
            addSourcesToTabLayout(it)
        }
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) {
            showNews(it)
        }
        newsViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            fragmentNewsDatabinding.mainActivityProgressBar.isVisible = it
        }
        newsViewModel.messageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
        newsViewModel.articleItemLiveData.observe(viewLifecycleOwner) {
           showNews(it)
        }
    }

    fun showNews(news_list: List<ArticlesItem?>?) {
        newsAdapter.changData(news_list!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    private fun initViews() {
        /*tab_layout = requireView().findViewById(R.id.tab_layout)
        progress_bar =  requireView().findViewById(R.id.main_activity_progress_bar)
        news_recycler_view = requireView().findViewById(R.id.news_recycler_view)*/
        fragmentNewsDatabinding.newsRecyclerView.adapter = newsAdapter

        newsAdapter.on_item_click_listeners = object : NewAdapter.OnItemClickListeners {
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
            val sources_tab_layout = fragmentNewsDatabinding.tabLayout.newTab()
            sources_tab_layout.setText(it?.name)
            sources_tab_layout.tag = it
            fragmentNewsDatabinding.tabLayout.addTab(sources_tab_layout)
        }
        fragmentNewsDatabinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                newsViewModel.getNewBySource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                newsViewModel.getNewBySource(source)
            }

        })

        fragmentNewsDatabinding.tabLayout.getTabAt(0)?.select()

    }


}