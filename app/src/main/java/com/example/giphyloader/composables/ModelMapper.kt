package com.example.giphyloader.composables

import com.example.giphyloader.network.models.GifModel
import com.example.giphyloader.network.models.GiphyResponse

fun GiphyResponse.asGifModelList(): List<GifModel> =
    this.data.map {
        GifModel(
            id = it.id,
            url = it.images.fixedWidth.url,
            width = it.images.fixedHeight.width,
            height = it.images.fixedWidth.height,
        )
    }
