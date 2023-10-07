package com.example.giphyloader.viewmodels

import androidx.lifecycle.ViewModel
import com.example.giphyloader.common.sampleImages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(): ViewModel() {
    val gifList = sampleImages
}