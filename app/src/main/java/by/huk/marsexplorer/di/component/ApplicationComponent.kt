package by.huk.marsexplorer.di.component


import by.huk.marsexplorer.di.module.AppModule
import by.huk.marsexplorer.di.module.CiceroneModule
import by.huk.marsexplorer.di.module.NetModule
import by.huk.marsexplorer.di.module.PresenterModule
import by.huk.marsexplorer.ui.MainActivity
import by.huk.marsexplorer.ui.details.DetailsFragment
import by.huk.marsexplorer.ui.main.MainFragment
import by.huk.marsexplorer.ui.map.MapFragment
import by.huk.marsexplorer.ui.splash.SplashScreenFragment

import dagger.Component

@Component(modules = [AppModule::class,NetModule::class,PresenterModule::class,CiceroneModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailsFragment)
    fun inject(mapFragment: MapFragment)
    fun inject(splashScreenFragment: SplashScreenFragment)
}