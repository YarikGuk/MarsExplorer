package by.huk.marsexplorer.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.huk.marsexplorer.R
import by.huk.marsexplorer.databinding.FragmentMapBinding
import by.huk.marsexplorer.ui.adapters.MarkerAdapter
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapFragment : Fragment(), MapContractsView {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: IMapPresenter
    private lateinit var bottomSheet: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter()
    }

    private fun getPresenter(): IMapPresenter {
        presenter = MapPresenterImpl(this, requireContext())
        return presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
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

        binding.landscape.setOnClickListener { presenter.showPopup(view,R.menu.popup_menu,requireContext())}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun attachAdapter(adapter: MarkerAdapter) {
        binding.bottomSheet.bottomSheetRecycler.adapter = adapter
    }




}
