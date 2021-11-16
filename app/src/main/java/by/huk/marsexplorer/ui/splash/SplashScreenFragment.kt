package by.huk.marsexplorer.ui.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.huk.marsexplorer.App
import by.huk.marsexplorer.databinding.FragmentSpalshScreenBinding
import by.huk.marsexplorer.utils.Screens

class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSpalshScreenBinding? = null
    private val binding get() = _binding!!
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

    private fun isNetworkEnable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

    private fun checkNetwork() {
        if (isNetworkEnable(requireContext())) {
            App.INSTANCE.router.newRootScreen(Screens.main())
            parentFragmentManager.popBackStack()
        } else {
            with(binding) {
                repeatBtn.visibility = View.VISIBLE
                iconSplash.visibility = View.VISIBLE
                messageSplash.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}