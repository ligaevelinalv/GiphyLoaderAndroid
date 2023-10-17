package com.example.giphyloader.repository

import com.example.giphyloader.composables.asGifModelList
import com.example.giphyloader.network.DataSource
import com.example.giphyloader.network.models.GifModel

interface GifRepository {
    suspend fun searchQuery(query: String, offset: String): List<GifModel>
}

class GifRepositoryImpl(
    private val dataSource: DataSource,
) : GifRepository {

    override suspend fun searchQuery(query: String, offset: String): List<GifModel> {
        return dataSource.searchQuery(query, offset).asGifModelList()
    }
}
