package com.example.giphyloader.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.giphyloader.R
import com.example.giphyloader.network.models.GifModel

@Composable
fun GifGrid(
    modifier: Modifier = Modifier,
    gifList: LazyPagingItems<GifModel>,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = modifier,
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            count = gifList.itemCount,
            key = gifList.itemKey { it.id },
            contentType = gifList.itemContentType { "GifItems" },
        ) { index ->
            val item = gifList[index]
            if (item != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.url)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    contentDescription = "Gif ${index + 1}",
                    modifier = Modifier
                        .size(
                            width = pxToDp(item.width, LocalContext.current),
                            height = pxToDp(item.height, LocalContext.current),
                        )
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

fun pxToDp(pxValue: String, context: Context): Dp {
    return ((pxValue.toFloat() / context.resources.displayMetrics.density) * 2).toInt().dp
}
