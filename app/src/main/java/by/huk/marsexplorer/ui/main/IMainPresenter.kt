package by.huk.marsexplorer.ui.main

interface IMainPresenter {
    fun onItemCLick(photoUrl: String)
    fun attach(view:MainContractsView)
    fun onViewCreated()
    fun onDestroy()
}