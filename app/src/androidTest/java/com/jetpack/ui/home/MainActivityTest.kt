package com.jetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.R
import com.jetpack.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummy = DataDummy.generateRemoteDummyMovies()
    private val tvshowDummy = DataDummy.generateRemoteDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Movies")).perform(ViewActions.click())
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Movies")).perform(ViewActions.click())

        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummy.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        onView(withId(R.id.tv_detail_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_movie_description)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun loadTrailerMovie() {
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Movies")).perform(ViewActions.click())

        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        onView(withId(R.id.btn_detail_trailer)).perform(ViewActions.click())
    }

    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Movies")).perform(ViewActions.click())

        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshowDummy.size))
    }

    @Test
    fun loadDetailTvshow() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
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
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Movies")).perform(ViewActions.click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        onView(withId(R.id.btn_detail_trailer)).perform(ViewActions.click())
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.menu_fav)).perform(ViewActions.click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummy.size))


        pressBack()
    }

    @Test
    fun loadFavoriteTvshow() {
        onView(withId(R.id.menu_fav)).perform(ViewActions.click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshowDummy.size))

        pressBack()
    }
}