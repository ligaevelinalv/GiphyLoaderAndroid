package com.example.giphyloader.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyloader.common.SearchFieldState
import com.example.giphyloader.common.ViewState
import com.example.giphyloader.network.GifRepository
import com.example.giphyloader.network.models.GiphyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(private val gifRepository: GifRepository) : ViewModel() {
    private val _searchFieldState: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState.IDLE)
    val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState

    private val _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState.IDLE)
    val viewState: StateFlow<ViewState> = _viewState

    private val _inputText: MutableStateFlow<String> =
        MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _gifList = MutableStateFlow<List<GiphyItem>>(emptyList())
    val gifList = _gifList

    init {
        viewModelScope.launch {
            inputText.debounce(timeoutMillis = 500).collectLatest { input ->
                if (input.isBlank() || input.isEmpty()) {
                    _viewState.update { ViewState.IDLE }
                    return@collectLatest
                }

                //TODO: response handling
                _gifList.update {
                    gifRepository.searchQuery(input)
                }
                _viewState.update { ViewState.FETCHED }
            }
        }
    }

    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        _viewState.update { ViewState.LOADING }
        _searchFieldState.update { SearchFieldState.INPUT }
    }
    fun clearInput() {
        //TODO: cancel request
        _viewState.update { ViewState.IDLE }
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.EMPTY }
    }
}
