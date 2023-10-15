package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName
@kotlinx.serialization.Serializable
data class GiphyImages(
    @SerialName("fixed_width_downsampled")
    val fixed_width_downsampled: GiphyURL,
    @SerialName("fixed_height_downsampled")
    val fixed_height_downsampled: GiphyURL,
)