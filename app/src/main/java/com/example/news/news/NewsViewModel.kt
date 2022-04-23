package com.example.news.news


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.Constants
import com.example.news.api.ApiManger
import com.example.news.categories.Category
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import com.example.domain.model.SourcesItem
import com.example.news.repos.news.NewsRepository
import com.example.news.repos.sources.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var newsRepository: NewsRepository,
    var sourcesRepository: SourcesRepository
) : ViewModel() {
    val sourcesLivedata = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val articleItemLiveData = MutableLiveData<List<ArticlesItem?>?>()


    fun getNewsResources(category: Category) {
        viewModelScope.launch {
            progressBarLiveData.value = true
            try {
                val sourceResponse = sourcesRepository.getSources(category.id)
                progressBarLiveData.value = false
                sourcesLivedata.value = sourceResponse

            } catch (ex: Exception) {
                messageLiveData.value = ex.localizedMessage
            }

        }
    }
    /*ApiManger.getApis()
        .getSources(Constants.API_KEY, category.id)
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
                message_live_data.value = t.localizedMessage
                //Log.e("error",t.localizedMessage)
            }
        })
}*/

    fun getNewBySource(source: SourcesItem) {
        try {
            viewModelScope.launch {
                progressBarLiveData.value = true
                val newsResponse = newsRepository.getNews(source.id)
                progressBarLiveData.value = false
                newsLiveData.value = newsResponse
            }
        } catch (ex: Exception) {
            progressBarLiveData.value = false
            messageLiveData.value = ex.localizedMessage
        }
        /* progress_bar_live_data.value = true
         //progress_bar.isVisible = true
         ApiManger.getApis()
             .getNewsResources("Constants.API_KEY, source?.id ?: ")
             .enqueue(object : Callback<NewsResponse> {
                 override fun onResponse(
                     call: Call<NewsResponse>,
                     response: Response<NewsResponse>
                 ) {
                     progress_bar_live_data.value = false
                     //progress_bar.isVisible = false
                     //news_adapter.changData(response.body()?.articles)
                     news_live_data.value = response.body()?.articles
                 }

                 override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                     progress_bar_live_data.value = false
                     //progress_bar.isVisible = false
                 }
             }) */
    }

    fun searchNewsBySource(key_word: String, source: SourcesItem?) {
        progressBarLiveData.value = true
        ApiManger
            .getApis()
            .searchNewsResources(Constants.API_KEY, key_word, source?.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    articleItemLiveData.value = response.body()?.articles
                    progressBarLiveData.value = false
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    messageLiveData.value = t.localizedMessage
                }
            })
    }


}