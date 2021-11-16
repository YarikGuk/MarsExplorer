package by.huk.marsexplorer.ui.main

import by.huk.network.entities.crypto.PhotoEntity

interface MainContractsView {
    fun showProgress()
    fun hideProgress()
    fun showPhotos(list:List<PhotoEntity>)
    fun onItemClick(photoUrl: String)
}