package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PaginationObject(
    @SerialName("offset")
    val offset: Int,
    @SerialName("total_count")
    val total_count: Int,
    @SerialName("count")
    val count: Int
)
