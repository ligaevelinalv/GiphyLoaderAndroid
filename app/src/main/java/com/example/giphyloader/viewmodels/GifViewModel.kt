package com.example.giphyloader.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyloader.common.SearchFieldState
import com.example.giphyloader.network.GifRepository
import com.example.giphyloader.network.RequestState
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

    private val _inputText: MutableStateFlow<String> =
        MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _requestState = gifRepository.requestState
    val requestState = _requestState

    init {
        viewModelScope.launch {
            inputText.debounce(timeoutMillis = 500).collectLatest { input ->
                if (input.isBlank() || input.isEmpty()) {
                    _requestState.tryEmit(RequestState.Idle())
                    return@collectLatest
                }
                 gifRepository.searchQuery(input)
            }
        }
    }

    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        _searchFieldState.update { SearchFieldState.INPUT }
    }
    fun clearInput() {
        //TODO: cancel request
        _requestState.tryEmit(RequestState.Idle())
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.EMPTY }
    }
}
