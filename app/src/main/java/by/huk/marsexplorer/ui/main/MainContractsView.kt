package by.huk.marsexplorer.ui.main

import by.huk.marsexplorer.data.entities.crypto.PhotoEntity

interface MainContractsView {
    fun showProgress()
    fun hideProgress()
    fun showPhotos(list:List<PhotoEntity>)

}