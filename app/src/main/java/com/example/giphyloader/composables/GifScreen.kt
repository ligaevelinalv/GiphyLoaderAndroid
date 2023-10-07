package com.example.giphyloader.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.giphyloader.viewmodels.GifViewModel

@Composable
fun GifScreen(
    modifier: Modifier = Modifier,
    viewModel: GifViewModel = hiltViewModel()
) {
    var query by rememberSaveable { mutableStateOf("") }
    Column {
        SearchBar(
            modifier = modifier.padding(16.dp),
            query
        ) { query = it }
        GifGrid(
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            viewModel.gifList
        )
    }
}
