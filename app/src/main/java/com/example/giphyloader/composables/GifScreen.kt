package com.example.giphyloader.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.giphyloader.common.ViewState
import com.example.giphyloader.viewmodels.GifViewModel

@Composable
fun GifScreen(
    modifier: Modifier = Modifier,
    viewModel: GifViewModel = hiltViewModel()
) {
    val gifList by viewModel.gifList.collectAsState()
    val viewState = viewModel.viewState.collectAsState().value
    val searchFieldState = viewModel.searchFieldState.collectAsState().value
    val inputText = viewModel.inputText.collectAsState().value
    val focusManager = LocalFocusManager.current

    Column {
        SearchBar(
            inputText = inputText,
            searchFieldState = searchFieldState,
            modifier = modifier.padding(16.dp),
            onSearchInputChanged = { input -> viewModel.updateInput(input) },
            onClearInputClicked = { viewModel.clearInput() }
        )

        LaunchedEffect(viewState) {
            if (viewState == ViewState.FETCHED) {
                focusManager.clearFocus()
            }
        }
        when (viewState) {
            ViewState.IDLE -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Start searching")
                }
            }

            ViewState.ERROR -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error :(")
                }
            }

            ViewState.LOADING -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            ViewState.FETCHED -> {
                GifGrid(
                    modifier = modifier.padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                    gifList
                )
            }
        }
    }
}
