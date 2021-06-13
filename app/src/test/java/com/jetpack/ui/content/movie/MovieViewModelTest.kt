package com.jetpack.ui.content.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.utils.DataDummy
import com.jetpack.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel : MovieViewModel
    private val movie = Resource.success(DataDummy.generateDummyMovies()[0])
    private val movieId = DataDummy.generateDummyMovies()[0].movieId

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
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getAllMovies() {
        val movieDummy = Resource.success(movies)
        `when`(movieDummy.data?.size).thenReturn(5)

        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = movieDummy

        `when`(repository.getAllMovies()).thenReturn(movie)
        val movieEntity = viewModel.getAllMovies().value?.data
        verify(repository).getAllMovies()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(5, movieEntity?.size)
    }

    @Test
    fun setFavMovie() {
        val movieDummy = MutableLiveData<Resource<MovieEntity>>()
        movieDummy.value = movie

        `when`(repository.getMovieDetail(movieId.toInt())).thenReturn(movieDummy)

        viewModel.setFav(movie.data!!)
        verify(repository).setFavMovie(movieDummy.value!!.data as MovieEntity)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun getDetail() {
        val movieDummy = MutableLiveData<Resource<MovieEntity>>()
        movieDummy.value = movie

        `when`(repository.getMovieDetail(movieId.toInt())).thenReturn(movieDummy)
        val item = viewModel.getDetail(movieId.toInt()).value

        Assert.assertNotNull(item)
        Assert.assertEquals(movieDummy.value!!.data?.movieId, item?.data?.movieId)
        Assert.assertEquals(movieDummy.value!!.data?.title, item?.data?.title)
        Assert.assertEquals(movieDummy.value!!.data?.description, item?.data?.description)
        Assert.assertEquals(movieDummy.value!!.data?.genre, item?.data?.genre)
        Assert.assertEquals(movieDummy.value!!.data?.year, item?.data?.year)
        Assert.assertEquals(movieDummy.value!!.data?.poster, item?.data?.poster)
    }
}