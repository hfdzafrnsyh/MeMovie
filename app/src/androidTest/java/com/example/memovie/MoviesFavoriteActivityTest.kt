package com.example.memovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.memovie.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class MoviesFavoriteActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MoviesFavoriteActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MoviesFavoriteActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun loadMovieFavorite() {
        Espresso.onView(withId(R.id.rvMovieFavorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovieFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }


    @Test
    fun loadTvShowFavorite() {
        Espresso.onView(withText("Tv Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTvShowFavorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShowFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }


}