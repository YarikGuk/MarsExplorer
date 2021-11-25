package by.huk.marsexplorer.ui.main

import by.huk.network.entities.crypto.PhotoEntity
import by.huk.network.source.dto.mappers.MarsResponseMapper
import by.huk.marsexplorer.utils.Screens
import by.huk.network.model.Model
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenterImpl(private val model: Model,private val router: Router):IMainPresenter {
    lateinit var view: MainContractsView
    private val disposable = CompositeDisposable()
    private val initList = mutableListOf<PhotoEntity>()


    private fun getPhotoList() {
        disposable.add(model.getPhotoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val list = response?.photos?.map {
                    MarsResponseMapper().map(it)
                }
                if (list != null) {
                    initList(list)
                    initList.addAll(list)
                }
                view.hideProgress()
            }.subscribe({}, {}))
    }

    private fun initList(list: List<PhotoEntity>) {
        view.showPhotos(list)
    }

    override fun onItemCLick(photoUrl: String) {
        router.navigateTo(Screens.details(photoUrl))
    }

    override fun attach(view: MainContractsView) {
        this.view = view
    }

    override fun onViewCreated() {
        if (!initList.isNullOrEmpty()) {
            initList(initList)
            view.hideProgress()
        } else {
            getPhotoList()
            view.showProgress()
        }
    }

    override fun onDestroy() {
        disposable.clear()
        disposable.dispose()
    }
}