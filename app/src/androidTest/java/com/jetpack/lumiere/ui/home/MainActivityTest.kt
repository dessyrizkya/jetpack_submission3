package com.jetpack.lumiere.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.lumiere.R
import com.jetpack.lumiere.utils.DataDummy
import com.jetpack.lumiere.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummy = DataDummy.generateRemoteDummyMovies()
    private val tvshowDummy = DataDummy.generateRemoteDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummy.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withText("TV Shows")).perform(click())
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.tv_detail_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_description)).check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    fun loadTrailerMovie() {
        onView(withText("TV Shows")).perform(click())
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btn_detail_trailer)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshowDummy.size))
    }

    @Test
    fun loadDetailTvshow() {
        onView(withText("TV Shows")).perform(click())
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.img_detail_tvshow_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tvshow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tvshow_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tvshow_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tvshow_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tvshow_episode)).check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    fun loadTrailerTvshow() {
        onView(withText("TV Shows")).perform(click())
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btn_detail_trailer)).perform(click())
    }
}