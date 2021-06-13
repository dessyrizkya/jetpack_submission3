package com.jetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.R
import com.jetpack.utils.DataDummy
import com.jetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
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
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummy.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                ViewActions.click()
            ))

        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.img_detail_movie_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_movie_year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_movie_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_movie_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }

    @Test
    fun loadTrailerMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_detail_trailer)).perform(ViewActions.click())
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshowDummy.size))
    }

    @Test
    fun loadDetailTvshow() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                ViewActions.click()
            ))

        Espresso.onView(ViewMatchers.withId(R.id.img_detail_tvshow_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_tvshow_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_tvshow_year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_tvshow_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_tvshow_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_tvshow_episode))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun loadTrailerTvshow() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_detail_trailer)).perform(ViewActions.click())
    }

    @Test
    fun loadFavoriteMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.menu_fav)).perform(ViewActions.click())
        Espresso.pressBack()
    }

    @Test
    fun loadFavoriteTvshow() {
        Espresso.onView(ViewMatchers.withId(R.id.menu_fav)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV Shows")).perform(ViewActions.click())
        Espresso.pressBack()
    }
}