package by.huk.marsexplorer.utils

import by.huk.marsexplorer.ui.details.DetailsFragment
import by.huk.marsexplorer.ui.details.HintFragment
import by.huk.marsexplorer.ui.main.MainFragment
import by.huk.marsexplorer.ui.map.MapFragment
import by.huk.marsexplorer.ui.splash.SplashScreenFragment
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun main() = FragmentScreen { MainFragment() }
    fun splash(navigator: AppNavigator) = FragmentScreen { SplashScreenFragment(navigator) }
    fun map() = FragmentScreen { MapFragment() }
    fun details(photoUrl:String) = FragmentScreen { DetailsFragment(photoUrl) }
    fun hint(photoUrl: String) = FragmentScreen { HintFragment(photoUrl) }

}