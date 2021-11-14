package by.huk.marsexplorer.ui.map

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import by.huk.marsexplorer.R
import by.huk.marsexplorer.isDisplayed
import junit.framework.TestCase
import org.hamcrest.core.IsAnything.anything
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MapFragmentTest : TestCase(){
    lateinit var scenario: FragmentScenario<MapFragment>

    @Before
    fun init(){
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }
    @Test
    fun popupTest() {
        onView(withId(R.id.landscape)).perform(click())
        onData(anything()).atPosition(1).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }
    @Test
    fun bottomSheetTest(){
        onView(withId(R.id.bottom_sheet_recycler)).isDisplayed()
    }

}