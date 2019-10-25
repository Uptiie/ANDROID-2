package com.example.watermyplants

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PlantListUITest: UITestBase() {

//    @Rule
//    @JvmField
//    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun plantCheck() {
        // Setup
        val nickname = "George"
        val species = "Bamboo"
        val h2OFrequency = "I need to be watered every day at 12"

        // Check
        onView(withId(R.id.tv_plant_nickname)).check(matches(withText(nickname)))
        onView(withId(R.id.tv_plant_species)).check(matches(withText(species)))
        onView(withId(R.id.tv_plant_frequency)).check(matches(withText(h2OFrequency)))
    }
}