package by.huk.marsexplorer.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R
import by.huk.marsexplorer.databinding.FragmentDetailsBinding
import by.huk.marsexplorer.utils.Screens
import com.bumptech.glide.Glide

class DetailsFragment(private val photoUrl: String) : Fragment(), DetailContractView {

    private lateinit var presenter: IDetailPresenter
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
        Glide.with(requireContext()).load(photoUrl).into(binding.photo)


        binding.goBackBtn.setOnClickListener { presenter.onButtonClick(it) }
        binding.shareBtn.setOnClickListener { presenter.onButtonClick(it) }
    }

    private fun getPresenter(): IDetailPresenter {
        presenter = DetailPresenterImpl(this)
        return presenter
    }

    override fun popBackStack() {
        App.INSTANCE.router.backTo(Screens.main())
    }

    override fun showHint() {
        App.INSTANCE.router.navigateTo(Screens.hint(photoUrl))
    }

    override fun sharePhoto() {
        val message = "Look at this!\n$photoUrl"
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_link)))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}






