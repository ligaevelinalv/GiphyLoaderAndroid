package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyURL(
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: String,
    @SerialName("height")
    val height: String
)
