package com.example.giphyloader.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.giphyloader.viewmodels.GifViewModel

@Composable
fun GifScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: GifViewModel = hiltViewModel()
    val gifList by viewModel.gifList.collectAsState()
    Column {
        SearchBar(
            viewModel.query,
            modifier = modifier.padding(16.dp),
        ) { input ->
            Log.i("package:mine", "Input $input")
            viewModel.updateQuery(input)
        }
        GifGrid(
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            gifList
        )
    }
}
