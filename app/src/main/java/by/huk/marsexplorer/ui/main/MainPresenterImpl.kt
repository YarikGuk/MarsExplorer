package by.huk.marsexplorer.ui.main

import android.util.Log
import by.huk.marsexplorer.App
import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.data.source.dto.mappers.MarsResponseMapper
import by.huk.marsexplorer.utils.Screens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenterImpl(mainFragment: MainFragment):IMainPresenter {
    private var view: MainContractsView = mainFragment
    private val model = App.INSTANCE.appComponent.model
    private val router = App.INSTANCE.router
    private val disposable = CompositeDisposable()
    private val initList = mutableListOf<PhotoEntity>()

    private fun getPhotoList() {
        disposable.add(model.getPhotoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { response ->
                val list = response?.photos?.map {
                    MarsResponseMapper().map(it)
                }
                if (list != null) {
                    initList(list)
                    initList.addAll(list)
                }
                view.hideProgress()
                Observable.fromIterable(list as @NonNull MutableIterable<*>)
                    .subscribeOn(Schedulers.io())
            }.subscribe({}, {}))
    }

    private fun initList(list: List<PhotoEntity>) {
        view.showPhotos(list)
    }

    override fun onItemCLick(photoUrl: String) {
        router.navigateTo(Screens.details(photoUrl))
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