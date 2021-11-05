package by.huk.marsexplorer.utils

import by.huk.marsexplorer.ui.SplashScreenFragment
import by.huk.marsexplorer.ui.details.DetailsFragment
import by.huk.marsexplorer.ui.details.HintFragment
import by.huk.marsexplorer.ui.main.MainFragment
import by.huk.marsexplorer.ui.map.MapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun splash() = FragmentScreen { SplashScreenFragment() }
    fun main() = FragmentScreen { MainFragment() }
    fun map() = FragmentScreen { MapFragment() }
    fun details(photoUrl:String) = FragmentScreen { DetailsFragment(photoUrl) }
    fun hint(photoUrl: String) = FragmentScreen { HintFragment(photoUrl) }
}