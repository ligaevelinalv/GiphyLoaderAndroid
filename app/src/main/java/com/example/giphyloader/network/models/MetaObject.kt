package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MetaObject(
    @SerialName("msg")
    val msg: String = "",
    @SerialName("status")
    val status: Int,
    @SerialName("response_id")
    val responseId: String = "",
)
