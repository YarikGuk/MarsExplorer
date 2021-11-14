package by.huk.marsexplorer

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

fun ViewInteraction.click() {
    this.perform(ViewActions.click())
}
fun ViewInteraction.isDisplayed() {
    this.check(matches(ViewMatchers.isDisplayed()))
}
