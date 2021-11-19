package by.huk.marsexplorer.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.huk.marsexplorer.R
import by.huk.marsexplorer.appComponent
import by.huk.marsexplorer.databinding.ActivityMainBinding
import by.huk.marsexplorer.utils.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragment_container)
    private val splashNavigator = AppNavigator(this, R.id.container_splash)

    private lateinit var bottomNavigationBar: BottomNavigationView

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)

        bottomNavigationBar = binding.bottomNavBar
        bottomNavigationBar.itemIconTintList = null

        navigatorHolder.setNavigator(splashNavigator)
        router.navigateTo(Screens.splash(navigator))

        bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_main -> {
                    router.newRootChain(Screens.main())
                }
                R.id.navigation_map -> {
                    router.newRootChain(Screens.map())
                }
            }
            true
        }
    }
    override fun onRestart() {
        super.onRestart()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}