package com.example.giphyloader.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.giphyloader.R
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
    val clearFocus = {
        focusManager.clearFocus()
    }

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
                clearFocus()
            }
        }

        /*
        This when block uses the viewState and gifList variables to determine what to show under the
        search field based on the state of app. gifList itself is not enough to determine the state
        because it does not have default implementations for network loss, idle and success state
         */
        when {
            viewState == ViewState.IDLE -> {
                ViewStatusCard(drawable = R.drawable.ic_search, text = null)
            }

            gifList.loadState.refresh is LoadState.Error -> {
                clearFocus()
                ViewStatusCard(drawable = R.drawable.ic_error, text = R.string.error)
            }

            gifList.loadState.refresh is LoadState.Loading -> {
                LoadingIndicator()
            }

            else -> {
                if (gifList.itemSnapshotList.size == 0) {
                    clearFocus()
                    ViewStatusCard(drawable = R.drawable.ic_error, text = R.string.error)
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
