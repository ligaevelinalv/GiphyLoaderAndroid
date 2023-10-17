package com.example.giphyloader.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.giphyloader.common.ViewState
import com.example.giphyloader.viewmodel.GifViewModel

@Composable
fun GifScreen(
    modifier: Modifier = Modifier,
    viewModel: GifViewModel = hiltViewModel(),
) {
    val gifList = viewModel.gifList.collectAsLazyPagingItems()
    val searchFieldState = viewModel.searchFieldState.collectAsState().value
    val viewState = viewModel.viewState.collectAsState().value
    val inputText = viewModel.query.collectAsState().value
    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    Column {
        SearchBar(
            inputText = inputText,
            searchFieldState = searchFieldState,
            modifier = modifier.padding(16.dp),
            onSearchInputChanged = { input -> viewModel.updateInput(input) },
            onClearInputClicked = { viewModel.clearInput() },
        )

        LaunchedEffect(viewState) {
            if (viewState == ViewState.IDLE) {
                focusManager.clearFocus()
            }
        }

        LaunchedEffect(scrollState) {
            Log.i("AAAA", "fsdgds")
        }

        when {
            viewState == ViewState.IDLE -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Start searching")
                }
            }

            gifList.loadState.refresh is LoadState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error :(")
                }
            }

            gifList.loadState.refresh is LoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                if (gifList.itemSnapshotList.size == 0) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error :(")
                    }
                }
                GifGrid(
                    modifier = modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                    ),
                    gifList,
                )
            }
        }
    }
}
