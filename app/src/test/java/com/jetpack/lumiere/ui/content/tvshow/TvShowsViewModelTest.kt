package com.jetpack.lumiere.ui.content.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.ui.content.movie.MoviesViewModel
import com.jetpack.lumiere.ui.content.tvshow.TvShowsViewModel
import com.jetpack.lumiere.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    private val dummyDetail = DataDummy.generateDummyTvShowsDetail()[0]
    private val tvId = dummyDetail.tvshowId.toInt()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var lumiereRepository: LumiereRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Mock
    private lateinit var observerDetail: Observer<TvShowDetailEntity>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(lumiereRepository)
    }

    @Test
    fun getTvShows() {
        val dummy = DataDummy.generateDummyTvShows()
        val tvshows = MutableLiveData<List<TvShowEntity>>()
        tvshows.value = dummy

        Mockito.`when`(lumiereRepository.getAllTvShows()).thenReturn(tvshows)
        val tvEntities = viewModel.getTvShows().value
        Mockito.verify(lumiereRepository).getAllTvShows()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }

    @Test
    fun getTvshowDetail() {
        val tvshow = MutableLiveData<TvShowDetailEntity>()
        tvshow.value = dummyDetail

        Mockito.`when`(lumiereRepository.getTvDetail(tvId)).thenReturn(tvshow)

        val data = viewModel.getTvShow(tvId).value as TvShowDetailEntity

        assertNotNull(data)
        assertEquals(dummyDetail.tvshowId, data.tvshowId)
        assertEquals(dummyDetail.title, data.title)
        assertEquals(dummyDetail.description, data.description)
        assertEquals(dummyDetail.year, data.year)
        assertEquals(dummyDetail.genre, data.genre)
        assertEquals(dummyDetail.poster, data.poster)
        assertEquals(dummyDetail.episode, data.episode)

        viewModel.getTvShow(tvId).observeForever(observerDetail)
        Mockito.verify(observerDetail).onChanged(dummyDetail)
    }

}