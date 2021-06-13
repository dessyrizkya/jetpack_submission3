package com.jetpack.ui.content.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel : TvShowViewModel
    private val tvshow = Resource.success(DataDummy.generateDummyTvShows()[0])
    private val tvshowId = DataDummy.generateDummyTvShows()[0].tvshowId

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
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getAllTvShows() {
        val tvDummy = Resource.success(tvshows)
        Mockito.`when`(tvDummy.data?.size).thenReturn(5)
        val tvshow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvshow.value = tvDummy

        Mockito.`when`(repository.getAllTvShows()).thenReturn(tvshow)
        val tvEntity = viewModel.getAllTvShows().value?.data
        verify(repository).getAllTvShows()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)
    }

    @Test
    fun setFav() {
        val tvDummy = MutableLiveData<Resource<TvShowEntity>>()
        tvDummy.value = tvshow

        Mockito.`when`(repository.getTvDetail(tvshowId.toInt())).thenReturn(tvDummy)

        viewModel.setFav(tvshow.data!!)
        verify(repository).setFavMovie(tvDummy.value!!.data as MovieEntity)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun getDetail() {
        val tvDummy = MutableLiveData<Resource<TvShowEntity>>()
        tvDummy.value = tvshow

        Mockito.`when`(repository.getTvDetail(tvshowId.toInt())).thenReturn(tvDummy)
        val item = viewModel.getDetail(tvshowId.toInt()).value

        assertNotNull(item)
        assertEquals(tvDummy.value!!.data?.tvshowId, item?.data?.tvshowId)
        assertEquals(tvDummy.value!!.data?.title, item?.data?.title)
        assertEquals(tvDummy.value!!.data?.description, item?.data?.description)
        assertEquals(tvDummy.value!!.data?.genre, item?.data?.genre)
        assertEquals(tvDummy.value!!.data?.year, item?.data?.year)
        assertEquals(tvDummy.value!!.data?.poster, item?.data?.poster)
        assertEquals(tvDummy.value!!.data?.episode, item?.data?.episode)
    }
}