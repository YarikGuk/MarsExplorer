package by.huk.marsexplorer.di.component


import android.app.Application
import by.huk.marsexplorer.App
import by.huk.marsexplorer.data.model.Model
import by.huk.marsexplorer.di.module.AppModule
import by.huk.marsexplorer.di.module.NetModule
import by.huk.marsexplorer.ui.main.MainFragment
import by.huk.marsexplorer.ui.main.MainPresenter
import dagger.BindsInstance

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [NetModule::class,])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    val model :Model
}