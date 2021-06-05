package com.jetpack.lumiere.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jetpack.lumiere.data.source.remote.RemoteDataSource
import com.jetpack.lumiere.utils.DataDummy
import com.jetpack.lumiere.utils.LiveDataTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify

class LumiereRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val lumiereRepository = FakeLumiereRepository(remote)

    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = moviesResponse[1].movieId
    private val tvShowsResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvId = tvShowsResponse[1].tvId
    private val movieResponse = DataDummy.generateRemoteDummyMovieDetail()[1]
    private val tvShowResponse = DataDummy.generateRemoteDummyTvShowDetail()[1]

    @Test
    fun getAllMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesReceived(moviesResponse)
                null
            }.`when`(remote).getListMovie(any())
        }

        val data = LiveDataTestUtil.getValue(lumiereRepository.getAllMovies())

        runBlocking {
            verify(remote).getListMovie(any())
        }

        assertNotNull(data)
        assertEquals(moviesResponse.size.toLong(), data.size.toLong())
    }


    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(movieResponse)
                null
            }.`when` (remote).getDetailMovie(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(lumiereRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getDetailMovie(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.movieId.toString(), data.movieId)
    }

    @Test
    fun getAllTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onTvShowsReceived(tvShowsResponse)
                null
            }.`when`(remote).getListTvShow(any())
        }

        val data = LiveDataTestUtil.getValue(lumiereRepository.getAllTvShows())

        runBlocking {
            verify(remote).getListTvShow(any())
        }

        assertNotNull(data)
        assertEquals(moviesResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(tvShowResponse)
                null
            }.`when`(remote).getDetailTvShow(eq(tvId), any())
        }

        val data = LiveDataTestUtil.getValue(lumiereRepository.getTvDetail(tvId))

        runBlocking {
            verify(remote).getDetailTvShow(eq(tvId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.tvId.toString(), data.tvshowId)
    }
}