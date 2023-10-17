package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

/*
Root class that is used to serialize the API response
 */
@kotlinx.serialization.Serializable
data class GiphyResponse(
    @SerialName("data")
    val data: List<GiphyData> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationObject,
    @SerialName("meta")
    val meta: MetaObject,
)
