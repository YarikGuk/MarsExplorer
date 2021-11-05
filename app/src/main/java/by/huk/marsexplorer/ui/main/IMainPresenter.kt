package by.huk.marsexplorer.ui.main

interface IMainPresenter {
    fun onItemCLick(photoUrl: String)
    fun onViewCreated()
    fun onDestroy()
}