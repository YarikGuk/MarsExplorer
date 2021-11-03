package by.huk.marsexplorer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R
import by.huk.marsexplorer.databinding.ActivityMainBinding
import by.huk.marsexplorer.ui.main.MainFragment
import by.huk.marsexplorer.utils.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragment_container)
    private val router = App.INSTANCE.router
    private lateinit var bottomNavigationBar : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigationBar = binding.bottomNavBar
        bottomNavigationBar.itemIconTintList = null

        router.navigateTo(Screens.main())



        bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_main -> {
                    router.newChain()
                    router.navigateTo(Screens.main())
                }
                R.id.navigation_map -> {
                    router.newChain()
                    router.navigateTo(Screens.map())
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