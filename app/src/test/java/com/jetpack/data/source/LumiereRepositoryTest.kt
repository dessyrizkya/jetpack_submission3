package com.jetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jetpack.api.Api
import com.jetpack.data.local.LocalDataSource
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.remote.RemoteDataSource
import com.jetpack.data.utils.LiveDataTestUtil
import com.jetpack.data.utils.PagedListUtil
import com.jetpack.utils.DataDummy
import com.jetpack.vo.Resource
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class LumiereRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val api = mock(Api::class.java)
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val repository = FakeLumiereRepository(api, remote, local)

    private val listMovie = DataDummy.generateDummyMovies()
    private val listTvShow = DataDummy.generateDummyTvShows()
    private val movie = DataDummy.generateDummyMovies()[0]
    private val movieId = DataDummy.generateDummyMovies()[0].movieId
    private val tvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = DataDummy.generateDummyTvShows()[0].tvshowId

    @Test
    fun getAllMovies() {
        val datasourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when` (local.getAllMovies()).thenReturn(datasourceFactory)
        repository.getAllMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val detail = MutableLiveData<MovieEntity>()
        detail.value = movie
        `when`(local.getMovie(movieId)).thenReturn(detail)

        val movieEntity = LiveDataTestUtil.getValue(repository.getMovieDetail(movieId.toInt()))
        verify(local).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(movie.movieId, movieEntity.data?.movieId)
    }

    @Test
    fun getAllTvShows() {
        val datasourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when` (local.getAllTvShows()).thenReturn(datasourceFactory)
        repository.getAllTvShows()

        val tvshowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvshowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvshowEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val detail = MutableLiveData<TvShowEntity>()
        detail.value = tvShow
        `when`(local.getTv(tvShowId)).thenReturn(detail)

        val tvshowEntity = LiveDataTestUtil.getValue(repository.getTvDetail(tvShowId.toInt()))
        verify(local).getTv(tvShowId)
        assertNotNull(tvshowEntity)
        assertEquals(tvShow.tvshowId, tvshowEntity.data?.tvshowId)
    }

}