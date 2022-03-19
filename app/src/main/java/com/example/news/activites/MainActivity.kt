package com.example.news.activites
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Constants
import com.example.news.R
import com.example.news.news.NewAdapter
import com.example.news.api.ApiManger
import com.example.news.model.ArticlesItem
import com.example.news.model.NewsResponse
import com.example.news.model.SourceResponse
import com.example.news.model.SourcesItem
import com.example.news.newsitem.NewsItemActivity
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tab_layout : TabLayout
    lateinit var progress_bar : ProgressBar
    lateinit var news_recycler_view:RecyclerView
    lateinit var news_adapter : NewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize
        initViews()


        //functions
        getNewsResources()
    }

    private fun initViews() {
        tab_layout = findViewById(R.id.tab_layout)
        progress_bar = findViewById(R.id.main_activity_progress_bar)
        news_recycler_view =findViewById(R.id.news_recycler_view)
        news_adapter = NewAdapter(null)
        news_recycler_view.adapter=news_adapter

        news_adapter.on_item_click_listeners = object : NewAdapter.OnItemClickListeners{
            override fun onItemClick(position: Int, atrical_item: ArticlesItem) {
                val intent = Intent(this@MainActivity, NewsItemActivity::class.java)
                intent.putExtra(Constants.EXTRA_ITEM_DETAILS,atrical_item.description)
                intent.putExtra(Constants.EXTRA_ITEM_TITLE,atrical_item.title)
                intent.putExtra(Constants.EXTRA_ITEM_AUTHOR,atrical_item.author)
                intent.putExtra(Constants.EXTRA_ITEM_DATE,atrical_item.publishedAt)
                intent.putExtra(Constants.EXTRA_ITEM_IMAGE,atrical_item.urlToImage)
                startActivity(intent)
            }

        }

    }

    //function to get sources and display them in activity
    fun getNewsResources() {
        ApiManger.getApis()
            .getSources(Constants.API_KEY,"")
            .enqueue(object :  Callback<SourceResponse>{
                //response hege mn server hna
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                    progress_bar.isVisible = false
                    addSourcesToTabLayout(response.body()?.sources)
                    //Log.e("data",response.body().toString())
                }
                //fe h7alt lw el call fail w mafesh data gt
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    Log.e("error",t.localizedMessage)
                }
            })
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
                getNewBySource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        tab_layout.getTabAt(0)?.select()

    }
    //function to call second api
     fun getNewBySource(source: SourcesItem) {
         progress_bar.isVisible = true
         ApiManger.getApis()
             .getNewsResources(Constants.API_KEY,source?.id?:"")
             .enqueue(object :Callback<NewsResponse>{
                 override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                     progress_bar.isVisible = false
                     news_adapter.changData(response.body()?.articles)
                 }

                 override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                     progress_bar.isVisible = false
                 }
             })
    }


}