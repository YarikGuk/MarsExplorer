package by.huk.marsexplorer

import android.app.Application
import by.huk.marsexplorer.di.component.AppComponent
import by.huk.marsexplorer.di.component.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    lateinit var appComponent: AppComponent
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    var hintIsShowed = false

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}
