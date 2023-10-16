package com.example.giphyloader.network

import com.example.giphyloader.BuildConfig
import com.example.giphyloader.common.API_KEY
import com.example.giphyloader.network.models.GiphyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DataSource @Inject constructor(private val httpClient: HttpClient) {
    suspend fun searchQuery(query: String): GiphyResponse {
        return httpClient.get("${BuildConfig.BASE_URL}gifs/search?api_key=$API_KEY&q=$query&limit=50")
            .body()
    }
}
