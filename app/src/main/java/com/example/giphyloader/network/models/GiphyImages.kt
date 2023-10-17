package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyImages(
    @SerialName("fixed_width_downsampled")
    val fixedWidth: GiphyURL,
    @SerialName("fixed_height_downsampled")
    val fixedHeight: GiphyURL,
)
