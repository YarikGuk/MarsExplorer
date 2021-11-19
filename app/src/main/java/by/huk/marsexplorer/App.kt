package by.huk.marsexplorer

import android.app.Application
import android.content.Context
import by.huk.marsexplorer.di.component.AppComponent
import by.huk.marsexplorer.di.component.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    lateinit var appComponent: AppComponent
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

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

val Context.appComponent: AppComponent
    get() = when (this) {
       is App -> appComponent
        else -> this.applicationContext.appComponent
    }
