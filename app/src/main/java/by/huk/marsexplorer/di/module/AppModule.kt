package by.huk.marsexplorer.di.module

import android.app.Application
import android.content.Context
import androidx.core.view.ViewCompat
import by.huk.marsexplorer.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(var application: Application) {

    var applicationInst: Application = application

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return applicationInst
    }

}