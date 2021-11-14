package by.huk.marsexplorer.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.MenuRes
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import by.huk.marsexplorer.R
import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import by.huk.marsexplorer.utils.MARKER_HEIGHT
import by.huk.marsexplorer.utils.MARKER_WIDTH
import carbon.widget.EditText
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MapPresenterImpl(mapFragment: MapContractsView, val context: Context) : IMapPresenter {
    val view: MapContractsView = mapFragment
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
            setOnMapClickListener { showAlertDialog(googleMap, it) }
            setOnMarkerClickListener {
                it.showInfoWindow()
                false
            }
        }
    }

    private fun showAlertDialog(googleMap: GoogleMap, position: LatLng) {
        val editText = getEditText()
        val constraintLayout = getLayout(editText)



        MaterialAlertDialogBuilder(context)
            .setView(constraintLayout)
            .setPositiveButton(context.getString(R.string.save_btn)) { _, _ ->
                addMarker(googleMap,position,editText.text.toString())
            }
            .setNegativeButton(context.getString(R.string.cancel_btn)) { _, _ ->
                constraintLayout.removeAllViews()
            }
            .show()


    }

    private fun addMarker(googleMap: GoogleMap,position:LatLng,title:String) {
        val marker =
            googleMap.addMarker(MarkerOptions().position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)))
        adapter.addMarker(marker!!)
    }

    private fun getLayout(editText: EditText): ConstraintLayout {
        val constraintLayout = ConstraintLayout(context).apply {
            addView(editText,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            setPaddingRelative(50, 50, 50, 150)
        }
        return constraintLayout
    }

    private fun getEditText(): EditText {
        val editText = EditText(context).apply {
            setBackgroundTint(context.getColor(R.color.gradient_color))
            setTextColor(context.getColor(R.color.gradient_color))
            setHint(R.string.marker_name)
            setHintTextColor(context.getColor(R.color.gray_300))
            setPaddingRelative(50, 50, 50, 50)
        }
        return editText
    }

    override fun onViewCreated() {
        view.attachAdapter(adapter)
    }

    override fun showPopup(view: View, @MenuRes popupMenu: Int, context: Context?) {
        PopupMenu(context, view, Gravity.END, R.attr.actionOverflowMenuStyle, 0).apply {
            menuInflater.inflate(popupMenu, this.menu)
            setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.menu_normal -> setMapType(GoogleMap.MAP_TYPE_NORMAL)
                    R.id.menu_hybrid -> setMapType(GoogleMap.MAP_TYPE_HYBRID)
                    R.id.menu_satellite -> setMapType(GoogleMap.MAP_TYPE_SATELLITE)
                }
                true
            }
        }.show()
    }

    private fun setMapType(mapType: Int) {
        currentMapType = mapType
        googleMap?.mapType = mapType
    }
}