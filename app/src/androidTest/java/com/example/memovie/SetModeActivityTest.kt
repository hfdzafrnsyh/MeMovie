package com.example.memovie


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.junit.Test


import org.junit.Before

class SetModeActivityTest {


    @Before
    fun setUp(){
        ActivityScenario.launch(SetModeActivity::class.java)
    }

    @Test
    fun setThemeMode() {
        Espresso.onView(withId(R.id.tvSetMode)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.btnSetMode)).perform(click())
    }
}