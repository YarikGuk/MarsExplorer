package by.huk.marsexplorer.di.component


import by.huk.marsexplorer.data.model.Model
import by.huk.marsexplorer.di.module.NetModule
import by.huk.marsexplorer.ui.main.MainFragment

import dagger.Component

@Component(modules = [NetModule::class,])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    val model :Model
}