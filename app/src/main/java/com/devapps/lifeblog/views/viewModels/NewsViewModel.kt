package com.devapps.lifeblog.views.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapps.lifeblog.data.remote.models.NewsResponse
import com.devapps.lifeblog.repository.NewsRepository
import com.devapps.lifeblog.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) :
ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}