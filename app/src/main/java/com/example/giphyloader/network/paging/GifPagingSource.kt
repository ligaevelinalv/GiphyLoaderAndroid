package com.example.giphyloader.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphyloader.network.models.GifModel
import com.example.giphyloader.repository.GifRepository

class GifPagingSource(
    private val query: String,
    private val repository: GifRepository,
) : PagingSource<Int, GifModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifModel> {
        return try {
            val pageNumber = params.key ?: 0
            val response = repository.searchQuery(query, (pageNumber * params.loadSize).toString())

            LoadResult.Page(
                data = response,
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (response.isNotEmpty()) pageNumber + 1 else null,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
