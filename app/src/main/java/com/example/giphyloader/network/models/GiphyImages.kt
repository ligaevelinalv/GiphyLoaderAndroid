package com.example.giphyloader.network.models

import kotlinx.serialization.SerialName

/*
This app uses downsampled GIFs to get the best performance while displaying them. There is no
downsampled variant with unfixed dimensions therefore, in order to get the true dimensions of a
GIF for the best StaggeredGrid look, width is retrieved from fixed_height_downsampled
and height from fixed_width_downsampled
 */
@kotlinx.serialization.Serializable
data class GiphyImages(
    @SerialName("fixed_width_downsampled")
    val fixedWidth: GiphyURL,
    @SerialName("fixed_height_downsampled")
    val fixedHeight: GiphyURL,
)
