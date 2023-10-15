package com.example.giphyloader.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.giphyloader.R
import com.example.giphyloader.network.models.GiphyItem

@Composable
fun GifGrid(
    modifier: Modifier = Modifier,
    gifList: List<GiphyItem>
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = modifier,
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        content = {
            items(gifList, key = { it.id }) { item ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.images.fixed_width_downsampled.url)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(
                            width = pxToDp(
                                item.images.fixed_height_downsampled.width,
                                LocalContext.current
                            ),
                            height = pxToDp(
                                item.images.fixed_width_downsampled.height,
                                LocalContext.current
                            )
                        )
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    )
}

fun pxToDp(pxValue: String, context: Context): Dp {
    val screenPixelDensity = context.resources.displayMetrics.density
    var dpVal = pxValue.toFloat() / screenPixelDensity
    dpVal *= 2

    return dpVal.toInt().dp
}