package com.example.memovie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test


class TvshowDetailActivityTest {

    @Before
    fun setUp(){
        ActivityScenario.launch(TvshowDetailActivity::class.java)
    }


    @Test
    fun getDetailTvShow(){
        Espresso.onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvCategory)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitleDescription)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
    }
}