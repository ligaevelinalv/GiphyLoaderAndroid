package com.example.giphyloader.network

import android.util.Log
import com.example.giphyloader.network.models.GiphyItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

interface GifRepository {
    val requestState: MutableStateFlow<RequestState<List<GiphyItem>>>
    suspend fun searchQuery(query: String)
}

class GifRepositoryImpl(
    private val dataSource: DataSource
) : GifRepository {

    override val requestState = MutableStateFlow<RequestState<List<GiphyItem>>>(RequestState.Idle())
    override suspend fun searchQuery(query: String) {
        requestState.value = RequestState.Loading()
        try {
            requestState.value = RequestState.Success(dataSource.searchQuery(query).data)
        } catch (e: Exception) {
            Log.i("AAAAA","Error: $e")
            requestState.value = RequestState.Error(e.toString())
        }
    }
}
