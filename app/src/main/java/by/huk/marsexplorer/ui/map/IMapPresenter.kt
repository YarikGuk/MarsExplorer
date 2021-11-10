package by.huk.marsexplorer.ui.map

import android.content.Context
import android.view.View
import com.google.android.gms.maps.OnMapReadyCallback

interface IMapPresenter {
    fun setMapCallback():OnMapReadyCallback
    fun onViewCreated()
    fun showPopup(view: View, popupMenu: Int, context: Context?)
}