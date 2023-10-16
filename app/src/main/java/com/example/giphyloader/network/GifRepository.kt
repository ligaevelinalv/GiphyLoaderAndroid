package com.example.giphyloader.network

import com.example.giphyloader.network.models.GifModel
import com.example.giphyloader.network.models.asGifModelList
import kotlinx.coroutines.flow.MutableStateFlow

interface GifRepository {
    val requestState: MutableStateFlow<RequestState<List<GifModel>>>
    suspend fun searchQuery(query: String)
}

class GifRepositoryImpl(
    private val dataSource: DataSource,
) : GifRepository {

    override val requestState = MutableStateFlow<RequestState<List<GifModel>>>(RequestState.Idle())
    override suspend fun searchQuery(query: String) {
        requestState.value = RequestState.Loading()
        try {
            requestState.value =
                RequestState.Success(dataSource.searchQuery(query).asGifModelList())
        } catch (e: Exception) {
            requestState.value = RequestState.Error(e.toString())
        }
    }
}
