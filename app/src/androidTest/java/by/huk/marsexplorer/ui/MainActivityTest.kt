package by.huk.marsexplorer.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import by.huk.marsexplorer.R
import by.huk.marsexplorer.click
import by.huk.marsexplorer.isDisplayed
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase(){

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun clickMapButton_showMapFragment(){
        onView(withId(R.id.navigation_map)).click()
        onView(withId(R.id.landscape)).isDisplayed()
    }

    @Test
    fun clickMainButton_showMainFragment(){
        onView(withId(R.id.navigation_map)).click()
        onView(withId(R.id.navigation_main)).click()
        onView(withId(R.id.progress)).isDisplayed()
    }

}