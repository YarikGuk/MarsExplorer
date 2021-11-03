package by.huk.marsexplorer.ui.main

import android.util.Log
import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.data.model.Model
import by.huk.marsexplorer.data.source.dto.mappers.MarsResponseMapper
import by.huk.marsexplorer.di.component.AppComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter(val mainFragment: MainFragment) {
    var view:MainContractsView = mainFragment
    private val disposable = CompositeDisposable()
    lateinit var model: Model

    fun onCreate(appComponent: AppComponent) {
        this.model = appComponent.model
        getPhotoList()

    }


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
            }
            view.hideProgress()
            Observable.fromIterable(list as @NonNull MutableIterable<*>).subscribeOn(Schedulers.io())
        }.subscribe({},{}))
}

    private fun initList(list: List<PhotoEntity>){
        Log.e("TAG",list.size.toString())
        view.showPhotos(list)
    }
    fun onViewCreated() {
        view.showProgress()
    }

    fun onDestroy() {
        disposable.clear()
        disposable.dispose()
    }



}