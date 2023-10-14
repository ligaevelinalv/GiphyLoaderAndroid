package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName
@kotlinx.serialization.Serializable
data class GiphyImages(
    @SerialName("downsized_medium")
    val downsized_medium: GiphyURL,
)