package by.huk.marsexplorer.ui.map

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

interface IMapPresenter {
    fun setMapCallback():OnMapReadyCallback
    fun onViewCreated()
    fun attach(view: MapContractsView)
    fun setMapType(mapType: Int)
    fun addMarker(googleMap: GoogleMap, position: LatLng, title:String)
    fun onLandscapeButtonClick()
}