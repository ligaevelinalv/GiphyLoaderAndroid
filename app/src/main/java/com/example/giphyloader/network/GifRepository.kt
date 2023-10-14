package com.example.giphyloader.network

import android.util.Log
import com.example.giphyloader.composables.TAG
import com.example.giphyloader.network.models.GiphyItem
import com.example.giphyloader.network.models.GiphyResponse

interface GifRepository {
    suspend fun searchQuery(query: String): List<GiphyItem>
}

class GifRepositoryImpl(
    private val dataSource: DataSource
) : GifRepository {
    override suspend fun searchQuery(query: String): List<GiphyItem> {
        return dataSource.searchQuery(query).data
    }
}