package by.huk.marsexplorer.di.module

import android.app.Application
import android.content.Context
import by.huk.marsexplorer.App
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {

    @Provides
    fun provideContext(application: App):Context{
        return application.applicationContext
    }

    @Provides
    fun provideApp(): App {
        return App.INSTANCE
    }

    @Provides
    fun provideRouter(app: App):Router{
        return app.router
    }

    @Provides
    fun provideNavigatorHolder(app:App):NavigatorHolder{
        return app.navigatorHolder
    }

}