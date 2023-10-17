package com.example.giphyloader.composables

import com.example.giphyloader.network.models.GifModel
import com.example.giphyloader.network.models.GiphyResponse

fun GiphyResponse.asGifModelList(): List<GifModel> =
    this.data.map {
        GifModel(
            id = it.id,
            url = it.images.fixed_width_downsampled.url,
            width = it.images.fixed_height_downsampled.width,
            height = it.images.fixed_width_downsampled.height,
        )
    }
