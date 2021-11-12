

package by.huk.marsexplorer.ui.main

import android.view.View
import android.window.SplashScreen
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.intercepting.SingleActivityFactory
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R
import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.data.network.crypto.SpaceService
import by.huk.marsexplorer.data.source.dto.space.MarsResponse
import by.huk.marsexplorer.ui.MainActivity
import by.huk.marsexplorer.ui.adapters.PhotoAdapter
import by.huk.marsexplorer.ui.details.DetailPresenterImpl
import by.huk.marsexplorer.ui.details.DetailsFragment
import by.huk.marsexplorer.ui.map.MapFragment
import by.huk.marsexplorer.ui.splash.SplashScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoTestRule
import org.mockito.kotlin.mock
import java.security.spec.ECField
import javax.inject.Inject
//
@RunWith(AndroidJUnit4ClassRunner::class)
class MainPresenterTest : TestCase() {
//    private lateinit var presenterImpl: MainPresenterImpl
//    val scenario: FragmentScenario<MainFragment> = launchFragment()
//
//    @Before
//    fun setUpTests(){
//        scenario.onFragment(){
//        presenterImpl = MainPresenterImpl(it)
//        }
//        scenario.moveToState(Lifecycle.State.RESUMED)
//    }
//
//    @Test
//    fun displayNotEmptyList(){
//        val element = listOf(
//            PhotoEntity(anyInt(), anyString(), anyInt(), anyString(), anyString()),
//            PhotoEntity(anyInt(), anyString(), anyInt(), anyString(), anyString()),
//        )
//        scenario.onFragment(){
//            it.showPhotos(element)
//        }
//        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
//    }


//    @Test
//    fun progressIsShowing(){
//        presenter.showProgress()


//        verify(view).onItemClick(anyString())
//    }

//    @Test
//    fun evenTest(){
//        val mainPresenterImpl = MainPresenterImpl(MainFragment())
//        val result = mainPresenterImpl.onItemCLick("photo_url")
//        assertTrue(result)
//    }

//    val detailPresenterImpl = DetailPresenterImpl(mainFragment)
//    FragmentScenario.launchInContainer(SplashScreenFragment::class.java)
//        onView(withId(R.id.container_splash)).check(matches(isDisplayed()))
//    }
//}

//
//    @Test(expected = PerformException::class)
//    fun itemWithText_doesNotExist() {
//        onView(ViewMatchers.withId(R.id.recycler))
//            .perform(
//                // scrollTo will fail the test if no item matches.
//                RecyclerViewActions.scrollTo<PhotoAdapter.PhotoViewHolder>(
//                    hasDescendant(withText("not in the list"))
//                )
//            )
//    }
//
//
//    @Test
//    @SmallTest
//    fun testAddItemsWithoutMenuInflation() {
//        val navigation = BottomNavigationView(activityScenarioRule.getActivity())
//        activityTestRule.getActivity().setContentView(navigation)
//        navigation.menu.add("Item1")
//        navigation.menu.add("Item2")
//        assertEquals(2, navigation.menu.size())
//        navigation.menu.removeItem(0)
//        navigation.menu.removeItem(0)
//        assertEquals(0, navigation.menu.size())
//    }
//@Test fun scrollToItemBelowFold_checkItsText() {
//    // First, scroll to the position that needs to be matched and click on it.
//    onView(ViewMatchers.withId(R.id.recycler))
//        .perform(
//            RecyclerViewActions.actionOnItemAtPosition<PhotoAdapter.PhotoViewHolder>(
//                ITEM,
//                click()
//            )
//        )
//
//    // Match the text in an item below the fold and check that it's displayed.
//    val itemElementText = "${activityRule.activity.resources
//        .getString(R.string.item_element_text)} ${ITEM_BELOW_THE_FOLD.toString()}"
//    onView(withText(itemElementText)).check(matches(isDisplayed()))
//}
//
//    fun testShowProgress() {}
//
//    fun testHideProgress() {}
//
//    fun testShowPhotos() {}
//
//    fun testOnItemClick() {}
}
