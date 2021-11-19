package by.huk.marsexplorer.ui.map

import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

interface MapContractsView {
    fun attachAdapter(adapter: MarkerAdapter)
    fun showPopup()
    fun showDialog(googleMap: GoogleMap, position: LatLng)

}