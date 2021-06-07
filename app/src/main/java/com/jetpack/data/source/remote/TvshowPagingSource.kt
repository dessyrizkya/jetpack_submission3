package com.jetpack.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetpack.Api.Api
import com.jetpack.data.source.remote.response.TvResultsItem
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1
class TvshowPagingSource(private val api: Api) : PagingSource<Int, TvResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, TvResultsItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResultsItem> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = api.getTvShows(position)
            val tvshows = response.results

            LoadResult.Page(
                data = tvshows,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position-1,
                nextKey = if (tvshows.isEmpty()) null else position+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}