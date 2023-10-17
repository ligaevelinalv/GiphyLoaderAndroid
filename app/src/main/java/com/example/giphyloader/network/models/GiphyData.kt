package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyData(
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: GiphyImages,
)
