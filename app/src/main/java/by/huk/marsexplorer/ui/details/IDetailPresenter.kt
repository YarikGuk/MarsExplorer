package by.huk.marsexplorer.ui.details

import android.view.View
import android.widget.Button

interface IDetailPresenter {
    fun onButtonClick(button: View)
    fun onViewCreated()
}