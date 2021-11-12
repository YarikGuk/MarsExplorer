package by.huk.marsexplorer.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R
import by.huk.marsexplorer.databinding.ActivityMainBinding
import by.huk.marsexplorer.ui.splash.SplashScreenFragment
import by.huk.marsexplorer.utils.Screens
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragment_container)
    private val router = App.INSTANCE.router
    private lateinit var bottomNavigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationBar = binding.bottomNavBar
        bottomNavigationBar.itemIconTintList = null

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container_splash, SplashScreenFragment())
            .commit()

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


    override fun onResume() {
        super.onResume()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }
}