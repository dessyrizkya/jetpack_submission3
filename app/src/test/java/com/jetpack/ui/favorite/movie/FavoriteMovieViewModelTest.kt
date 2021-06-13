package com.jetpack.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.ui.content.movie.MovieViewModel
import com.jetpack.utils.DataDummy
import com.jetpack.vo.Resource
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel : FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: LumiereRepository

    @Mock
    private lateinit var movies: PagedList<MovieEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavMovies() {
        val movieDummy = movies
        `when`(movieDummy.size).thenReturn(5)

        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = movieDummy

        `when`(repository.getFavMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavMovies().value
        verify(repository).getFavMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)
    }
}