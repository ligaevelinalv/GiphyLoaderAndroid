package com.example.giphyloader.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.giphyloader.R

@Composable
fun GifGrid(
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = modifier,
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        content = {
            items( sampleImages ) { item ->
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(6.dp))
                )
            }
        }
    )
}

private val sampleImages = listOf(
    R.drawable.ab1_inversions,
    R.drawable.ab2_quick_yoga,
    R.drawable.ab3_stretching,
    R.drawable.ab4_tabata,
    R.drawable.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga,
    R.drawable.fc1_short_mantras,
    R.drawable.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage,
    R.drawable.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down,
    R.drawable.ab1_inversions,
    R.drawable.ab2_quick_yoga,
    R.drawable.ab3_stretching,
    R.drawable.ab4_tabata,
    R.drawable.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga,
    R.drawable.fc1_short_mantras,
    R.drawable.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage,
    R.drawable.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down
)
