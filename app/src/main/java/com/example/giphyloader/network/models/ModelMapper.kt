package com.example.giphyloader.network.models

fun GiphyResponse.asGifModelList(): List<GifModel> =
    this.data.map {
        GifModel(
            id = it.id,
            url = it.images.fixed_width_downsampled.url,
            width = it.images.fixed_height_downsampled.width,
            height = it.images.fixed_width_downsampled.height,
        )
    }
