package com.jetpack.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.ui.favorite.movie.FavoriteMovieViewModel
import com.jetpack.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvshowViewModelTest {

    private lateinit var viewModel : FavoriteTvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: LumiereRepository

    @Mock
    private lateinit var tvshows: PagedList<TvShowEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        viewModel = FavoriteTvshowViewModel(repository)
    }

    @Test
    fun getFavTvshows() {
        val tvDummy = tvshows
        Mockito.`when`(tvDummy.size).thenReturn(5)

        val tvshow = MutableLiveData<PagedList<TvShowEntity>>()
        tvshow.value = tvDummy

        Mockito.`when`(repository.getFavTvShows()).thenReturn(tvshow)
        val tvEntity = viewModel.getFavTvshows().value
        verify(repository).getFavTvShows()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)
    }
}