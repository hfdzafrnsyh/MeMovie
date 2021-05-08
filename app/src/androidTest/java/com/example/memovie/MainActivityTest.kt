package com.example.memovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.memovie.dummy.DataDummy
import com.example.memovie.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private var dummyMovie = DataDummy.generateDummyMovie()
    private var dummyTvshow = DataDummy.generateDummyTvShow()


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }


    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDate)).check(matches(withText(dummyMovie[0].date)))
        Espresso.onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun loadTvshow(){
        Espresso.onView(withText("Tv Show")).perform(click())
        Espresso.onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvshow.size))
    }

    @Test
    fun loadDetailTvshow() {
        Espresso.onView(withText("Tv Show")).perform(click())
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(dummyTvshow[0].title)))
        Espresso.onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDate)).check(matches(withText(dummyTvshow[0].date)))
        Espresso.onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun loadSettingMode(){
        Espresso.onView(withId(R.id.menu_setting)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.menu_setting)).perform(click())
    }

    @Test
    fun loadFavorite(){
        Espresso.onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.menu_favorite)).perform(click())
    }



}