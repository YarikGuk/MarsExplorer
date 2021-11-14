package by.huk.marsexplorer.ui.main

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import by.huk.marsexplorer.R
import by.huk.marsexplorer.ui.splash.SplashScreenFragment
import junit.framework.TestCase
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SplashScreenTest : TestCase() {

    lateinit var scenario: FragmentScenario<SplashScreenFragment>

    @Before
    fun init() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun splashIsWork(){
        onView(withId(R.id.message_splash)).check(matches(withText(R.string.network_off)))
    }
    @Test
    fun checkButtonVisibility(){
        onView(withId(R.id.repeat_btn)).check(matches(not(isDisplayed())))
    }

}


