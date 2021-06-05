package com.jetpack.lumiere.ui.content.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.remote.response.ResultsItem
import com.jetpack.lumiere.ui.content.movie.MoviesViewModel
import com.jetpack.lumiere.utils.DataDummy
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    private val dummyDetail = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyDetail.movieId.toInt()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var lumiereRepository: LumiereRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerDetail: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(lumiereRepository)
    }


    @Test
    fun getMovies() {
        val dummy = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummy

        `when`(lumiereRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(lumiereRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummy)
    }


    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyDetail

        `when`(lumiereRepository.getMovieDetail(movieId)).thenReturn(movie)

        val data = viewModel.getMovie(movieId).value as MovieEntity

        assertNotNull(data)
        assertEquals(dummyDetail.movieId, data.movieId)
        assertEquals(dummyDetail.title, data.title)
        assertEquals(dummyDetail.description, data.description)
        assertEquals(dummyDetail.year, data.year)
        assertEquals(dummyDetail.genre, data.genre)
        assertEquals(dummyDetail.poster, data.poster)

        viewModel.getMovie(movieId).observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyDetail)
    }

}