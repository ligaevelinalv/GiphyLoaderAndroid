package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyResponse(
    @SerialName("data")
    val data: List<GiphyItem> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationObject,
    @SerialName("meta")
    val meta: MetaObject,
)
