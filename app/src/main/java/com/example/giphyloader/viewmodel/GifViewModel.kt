package com.example.giphyloader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.giphyloader.common.SearchFieldState
import com.example.giphyloader.common.ViewState
import com.example.giphyloader.network.paging.GifPagingSource
import com.example.giphyloader.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val PAGE_SIZE = 50

@HiltViewModel
class GifViewModel @Inject constructor(private val gifRepository: GifRepository) : ViewModel() {
    private val _searchFieldState: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState.IDLE)
    val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState.IDLE)
    val viewState: StateFlow<ViewState> = _viewState

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val gifList =
        query.debounce(timeoutMillis = 500).flatMapLatest { input ->
            if (input.isBlank() || input.isEmpty()) {
                _viewState.tryEmit(ViewState.IDLE)
                return@flatMapLatest flowOf(PagingData.empty())
            }
            _viewState.tryEmit(ViewState.FETCHED)
            getGifs(input)
        }

    fun updateInput(inputText: String) {
        _query.update { inputText }
        _searchFieldState.update { SearchFieldState.INPUT }
    }

    fun clearInput() {
        _viewState.tryEmit(ViewState.IDLE)
        _query.update { "" }
        _searchFieldState.update { SearchFieldState.EMPTY }
    }

    private fun getGifs(query: String) = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        GifPagingSource(query, gifRepository)
    }.flow
}
