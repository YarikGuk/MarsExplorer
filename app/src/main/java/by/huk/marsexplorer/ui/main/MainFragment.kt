package by.huk.marsexplorer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.databinding.FragmentMainBinding

class MainFragment : Fragment(),MainContractsView {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter()
        mainPresenter.onCreate(requireActivity().appComponent)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainPresenter.onViewCreated()

        adapter = PhotoAdapter(requireContext())
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recycler.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainPresenter.onDestroy()
        _binding = null
    }

    private fun getPresenter():MainPresenter{
        mainPresenter = MainPresenter(this)
        return mainPresenter
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun showPhotos(list: List<PhotoEntity>) {
        adapter.initialize(list)
    }


}