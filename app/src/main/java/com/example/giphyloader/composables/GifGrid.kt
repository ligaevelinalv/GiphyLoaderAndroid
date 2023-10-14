package com.example.giphyloader.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
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
                        .data(item.images.downsized_medium.url)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.clip(
                        RoundedCornerShape(6.dp),
                    )
                )
            }
        }
    )
}
