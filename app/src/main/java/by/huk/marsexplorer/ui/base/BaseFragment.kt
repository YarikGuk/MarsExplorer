package by.huk.marsexplorer.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {


    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        attachPresenter()
    }
    abstract fun attachPresenter()
}