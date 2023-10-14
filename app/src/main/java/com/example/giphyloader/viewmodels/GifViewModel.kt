package com.example.giphyloader.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyloader.network.GifRepository
import com.example.giphyloader.network.models.GiphyItem
import com.example.giphyloader.network.models.GiphyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(private val gifRepository: GifRepository) : ViewModel() {
    private val _gifList = MutableStateFlow<List<GiphyItem>>(emptyList())
    val gifList = _gifList
    var query by mutableStateOf("")
        private set

    fun updateQuery(input: String) = viewModelScope.launch {
        query = input
        _gifList.update {
            gifRepository.searchQuery(input)
        }
    }
}
