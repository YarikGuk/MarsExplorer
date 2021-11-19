package by.huk.marsexplorer.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.huk.marsexplorer.R
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.databinding.FragmentSpalshScreenBinding
import by.huk.marsexplorer.utils.*
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class SplashScreenFragment(private val navigator: AppNavigator) : Fragment() {

    private var _binding: FragmentSpalshScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSpalshScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetwork()

        binding.repeatBtn.setOnClickListener {
            checkNetwork()
        }
    }

    private fun checkNetwork() {
        if (NetworkUtils.isNetworkEnable(requireContext())) {
            navigatorHolder.setNavigator(navigator)
            router.newRootScreen(Screens.main())
        } else {
            with(binding) {
                repeatBtn.isVisible(true)
                iconSplash.isVisible(true)
                messageSplash.isVisible(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}