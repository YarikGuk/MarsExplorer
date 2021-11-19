package by.huk.marsexplorer.di.module

import android.content.Context
import by.huk.marsexplorer.ui.details.DetailPresenterImpl
import by.huk.marsexplorer.ui.details.IDetailPresenter
import by.huk.marsexplorer.ui.main.IMainPresenter
import by.huk.marsexplorer.ui.main.MainContractsView
import by.huk.marsexplorer.ui.main.MainPresenterImpl
import by.huk.marsexplorer.ui.map.IMapPresenter
import by.huk.marsexplorer.ui.map.MapPresenterImpl
import by.huk.network.model.Model
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideMainPresenter(model: Model,router: Router):IMainPresenter{
        return MainPresenterImpl(model,router)
    }

    @Provides
    fun provideDetailPresenter():IDetailPresenter{
        return DetailPresenterImpl()
    }

    @Provides
    fun provideMapPresenter(context: Context):IMapPresenter{
        return MapPresenterImpl(context)
    }

}