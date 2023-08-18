package com.devapps.lifeblog.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapps.lifeblog.data.remote.models.Article
import com.devapps.lifeblog.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _state = MutableStateFlow(emptyList<Article>())
    val state: StateFlow<List<Article>>
        get() = _state

    init {
        viewModelScope.launch {
            val articles = newsRepository.getNewsArticles()
            _state.value = articles
        }
    }
}