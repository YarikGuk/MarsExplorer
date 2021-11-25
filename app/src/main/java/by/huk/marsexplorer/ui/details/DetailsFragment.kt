package by.huk.marsexplorer.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.huk.marsexplorer.R
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.databinding.FragmentDetailsBinding
import by.huk.marsexplorer.ui.base.BaseFragment
import by.huk.marsexplorer.utils.Screens
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsFragment(private val photoUrl: String) : BaseFragment<FragmentDetailsBinding>(), DetailContractView {

    @Inject
    lateinit var presenter: IDetailPresenter
    @Inject
    lateinit var router: Router

    override fun attachPresenter() {
        requireContext().appComponent.inject(this)
        presenter.attach(this)
    }
    override fun getViewBinding(): FragmentDetailsBinding {
        return  FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewCreated(requireContext())
        binding.goBackBtn.setOnClickListener { presenter.onButtonClick(it) }
        binding.shareBtn.setOnClickListener { presenter.onButtonClick(it) }
    }


    override fun popBackStack() {
        router.backTo(Screens.main())
    }

    override fun showHint() {
        router.navigateTo(Screens.hint(photoUrl))
    }

    override fun sharePhoto() {
        val message = "Look at this!\n$photoUrl"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_link)))
    }

    override fun loadImage() {
        Glide.with(requireContext()).load(photoUrl).into(binding.photo)
    }




}






