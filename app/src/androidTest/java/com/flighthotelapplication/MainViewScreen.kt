package com.flighthotelapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*

import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainViewScreen {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun bottomHotelsNavigation(){
        val res = activityRule.activity.resources

        onView(withId(R.id.navigation)).check(matches(isDisplayed())).perform(click())
        onView(
            allOf(
                withText(res.getString(R.string.title_hotel)),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()))
            .perform(click())
    }

    @Test
    fun hotelScreenTest(){
        var i = 0
        while (i<3){
            onView(withId(R.id.hotelRecyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(i))
            onView(RecyclerViewMatcher(R.id.hotelRecyclerView).atPositionOnView(i,R.id.hotel_images))
                .check(matches(isDisplayed()))
            i++
        }
        onView(withId(R.id.hotel_name)).check(matches(isDisplayed()))
            .check(matches(withText("")))
    }


    @Test
    fun recyclerFlightScreenTest(){
        var i = 0
        while (i<18){
        onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(i))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.flight_name))
                .check(matches(isDisplayed())).check(matches(anyOf(withText("FastJet"), withText("QuickJet"),
                    withText("SlowJet")))).check(matches(
                    hasTextColor(R.color.gold)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.imageView3))
                .check(matches(isDisplayed())).check(matches(DrawableSourceMatcher(R.drawable.ic_right_arrow)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView))
                .check(matches(isDisplayed())).check(matches(withText("London Gatwick"))).check(matches(
                    hasTextColor(R.color.orange)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView2))
                .check(matches(isDisplayed())).check(matches(anyOf(withText("Barcelona"),withText("Antalya"),withText("Girona")))).check(matches(
                    hasTextColor(R.color.orange)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView3))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(withText("12300"),withText("13300"),withText("12500"),withText("12350"),withText("12309"))))
                .check(matches(hasTextColor(R.color.gold)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView4))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(withText("10:00 AM"),withText("10:50 AM"),withText("22:00 PM"))))
                .check(matches(hasTextColor(R.color.white)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView5))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(withText("1H 0m"),withText("-22H 0m"))))
                .check(matches(hasTextColor(R.color.light_grey)))
            onView(RecyclerViewMatcher(R.id.recyclerView).atPositionOnView(i, R.id.textView6))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(withText("11:00 AM"),withText("11:50 AM"),withText("00:00 AM"))))
                .check(matches(hasTextColor(R.color.white)))
            i++
        }
    }
}