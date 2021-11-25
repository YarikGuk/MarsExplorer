package by.huk.marsexplorer.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import by.huk.marsexplorer.R
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.databinding.FragmentMainBinding
import by.huk.marsexplorer.ui.base.BaseFragment
import by.huk.marsexplorer.ui.adapters.PhotoAdapter
import by.huk.marsexplorer.utils.applyLoopingAnimatedVectorDrawable
import by.huk.marsexplorer.utils.isVisible
import by.huk.network.entities.crypto.PhotoEntity
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(), MainContractsView {

    private lateinit var adapter: PhotoAdapter

    @Inject
    lateinit var mainPresenter: IMainPresenter

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun attachPresenter() {
        requireContext().appComponent.inject(this)
        mainPresenter.attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = initRecycler(requireContext(), mainPresenter)
        mainPresenter.onViewCreated()

    }

    private fun initRecycler(context: Context, view: IMainPresenter): PhotoAdapter {
        val adapter = PhotoAdapter(context, view)
        with(binding.recycler) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
        return adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainPresenter.onDestroy()
    }

    override fun showProgress() {
        binding.progress.applyLoopingAnimatedVectorDrawable(R.drawable.progress_anim, true)
        binding.progress.isVisible(true)
    }

    override fun hideProgress() {
        binding.progress.applyLoopingAnimatedVectorDrawable(R.drawable.progress_anim, false)
        binding.progress.isVisible(false)
    }

    override fun showPhotos(list: List<PhotoEntity>) {
        adapter.initialize(list)
    }

    override fun onItemClick(photoUrl: String) {
        mainPresenter.onItemCLick(photoUrl)
    }




}