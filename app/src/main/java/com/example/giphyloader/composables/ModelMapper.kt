package com.example.giphyloader.composables

import com.example.giphyloader.network.models.GifModel
import com.example.giphyloader.network.models.GiphyResponse

/*
This method is used to "flatten" the data structure that was received from API call
into a list of objects that only contains the necessary parameters for displaying GIFs
 in the UI in the easiest way
 */
fun GiphyResponse.asGifModelList(): List<GifModel> =
    this.data.map {
        GifModel(
            id = it.id,
            url = it.images.fixedWidth.url,
            width = it.images.fixedHeight.width,
            height = it.images.fixedWidth.height,
        )
    }
