package by.huk.marsexplorer.ui.details

import android.content.Context
import android.view.View

interface IDetailPresenter {
    fun onButtonClick(button: View)
    fun onViewCreated(context: Context)
}