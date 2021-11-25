package by.huk.marsexplorer.ui.map

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import by.huk.marsexplorer.R
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.databinding.FragmentMapBinding
import by.huk.marsexplorer.ui.base.BaseFragment
import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import carbon.widget.EditText
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>(), MapContractsView {

    private lateinit var bottomSheet: BottomSheetBehavior<View>

    @Inject
    lateinit var presenter: IMapPresenter

    override fun attachPresenter() {
        requireContext().appComponent.inject(this)
        presenter.attach(this)
    }

    override fun getViewBinding(): FragmentMapBinding {
        return FragmentMapBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()


        bottomSheet = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet)).apply {
            isFitToContents = false
            halfExpandedRatio = 0.5f
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(presenter.setMapCallback())

        binding.landscape.setOnClickListener { presenter.onLandscapeButtonClick() }
    }

    override fun attachAdapter(adapter: MarkerAdapter) {
        binding.bottomSheet.bottomSheetRecycler.adapter = adapter
    }

    override fun showPopup() {
        PopupMenu(context, view, Gravity.END, R.attr.actionOverflowMenuStyle, 0).apply {
            menuInflater.inflate(R.menu.popup_menu, this.menu)
            setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.menu_normal -> presenter.setMapType(GoogleMap.MAP_TYPE_NORMAL)
                    R.id.menu_hybrid -> presenter.setMapType(GoogleMap.MAP_TYPE_HYBRID)
                    R.id.menu_satellite -> presenter.setMapType(GoogleMap.MAP_TYPE_SATELLITE)
                }
                true
            }
        }.show()
    }

    override fun showDialog(googleMap:GoogleMap,position:LatLng) {
        val editText = getEditText()
        val constraintLayout = getLayout(editText)

        MaterialAlertDialogBuilder(requireContext())
            .setView(constraintLayout)
            .setPositiveButton(requireContext().getString(R.string.save_btn)) { _, _ ->
                presenter.addMarker(googleMap,position,editText.text.toString())
            }
            .setNegativeButton(requireContext().getString(R.string.cancel_btn)) { _, _ ->
                constraintLayout.removeAllViews()
            }
            .show()
    }

    private fun getLayout(editText: EditText): ConstraintLayout {
        val constraintLayout = ConstraintLayout(requireContext()).apply {
            addView(editText,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            setPaddingRelative(50, 50, 50, 150)
        }
        return constraintLayout
    }

    private fun getEditText(): EditText {
        val editText = EditText(requireContext()).apply {
            setBackgroundTint(context.getColor(R.color.gradient_color))
            setTextColor(context.getColor(R.color.gradient_color))
            setHint(R.string.marker_name)
            setHintTextColor(context.getColor(R.color.gray_300))
            setPaddingRelative(50, 50, 50, 50)
        }
        return editText
    }


}
