package by.huk.marsexplorer.ui.map

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import by.huk.marsexplorer.R
import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import by.huk.marsexplorer.utils.MARKER_HEIGHT
import by.huk.marsexplorer.utils.MARKER_WIDTH
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*

class MapPresenterImpl(val context: Context) : IMapPresenter {
    lateinit var view: MapContractsView
    private val adapter = MarkerAdapter(this)
    private var googleMap: GoogleMap? = null
    private var currentMapType = GoogleMap.MAP_TYPE_NORMAL

    @SuppressLint("UseCompatLoadingForDrawables")
    private val markerBitmap =
        context.getDrawable(R.drawable.ic_marker)!!.toBitmap(MARKER_WIDTH, MARKER_HEIGHT)

    override fun setMapCallback(): OnMapReadyCallback {
        @SuppressLint("ResourceAsColor")
        val callback = OnMapReadyCallback { googleMap ->
            this.googleMap = googleMap
            with(googleMap) {
                uiSettings.isMapToolbarEnabled = false
                setMapStyle(MapStyleOptions.loadRawResourceStyle(context,
                    R.raw.map_style))
            }
            setClickListeners(googleMap)
        }
        return callback
    }

    private fun setClickListeners(googleMap: GoogleMap) {
        with(googleMap) {
            setOnMapClickListener { view.showDialog(googleMap, it) }
            setOnMarkerClickListener {
                it.showInfoWindow()
                false
            }
        }
    }


    override fun addMarker(googleMap: GoogleMap,position:LatLng,title:String) {
        val marker =
            googleMap.addMarker(MarkerOptions().position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)))
        adapter.addMarker(marker!!)
    }



    override fun onViewCreated() {
        view.attachAdapter(adapter)
    }

    override fun attach(view: MapContractsView) {
        this.view = view
    }

    override fun setMapType(mapType: Int) {
        currentMapType = mapType
        googleMap?.mapType = mapType
    }

    override fun onLandscapeButtonClick() {
        view.showPopup()
    }
}