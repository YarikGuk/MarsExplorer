package by.huk.marsexplorer.ui.map

import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import com.google.android.gms.maps.model.Marker

interface MapContractsView {
    fun attachAdapter(adapter: MarkerAdapter)

}