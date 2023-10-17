package com.example.giphyloader.common

/*
IDLE- view is not currently loading, displaying an error warning or showing list of GIFs
FETCHED- view has fetched and is showing the list of GIFs
 */
enum class ViewState {
    IDLE,
    FETCHED,
}
