package com.example.news.news


import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.Constants
import com.example.news.api.ApiManger
import com.example.news.categories.Category
import com.example.news.model.ArticlesItem
import com.example.news.model.NewsResponse
import com.example.news.model.SourceResponse
import com.example.news.model.SourcesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewsViewModel : ViewModel() {
    val sources_livedata  = MutableLiveData<List<SourcesItem?>?>()
    val news_live_data = MutableLiveData<List<ArticlesItem?>?>()
    val progress_bar_live_data = MutableLiveData<Boolean>()
    val message_live_data = MutableLiveData<String>()
    fun getNewsResources(category : Category) {
        ApiManger.getApis()
            .getSources(Constants.API_KEY,category.id)
            .enqueue(object : Callback<SourceResponse> {
                //response hege mn server hna
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                    progress_bar_live_data.value = false
                    //progress_bar.isVisible = false
                    //addSourcesToTabLayout(response.body()?.sources)
                    sources_livedata.value = response.body()?.sources
                }
                //fe h7alt lw el call fail w mafesh data gt
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                  message_live_data.value =t.localizedMessage
                //Log.e("error",t.localizedMessage)
                }
            })
    }
    fun getNewBySource(source: SourcesItem) {
        progress_bar_live_data.value = true
        //progress_bar.isVisible = true
        ApiManger.getApis()
            .getNewsResources(Constants.API_KEY,source?.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    progress_bar_live_data.value=false
                    //progress_bar.isVisible = false
                    //news_adapter.changData(response.body()?.articles)
                    news_live_data.value = response.body()?.articles
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                progress_bar_live_data.value = false
                //progress_bar.isVisible = false
                }
            })
    }


}