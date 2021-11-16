package by.huk.marsexplorer.ui.details

import android.content.Context
import android.view.View
import by.huk.marsexplorer.R

class DetailPresenterImpl(detailsFragment: DetailContractView) : IDetailPresenter {
    val view: DetailContractView = detailsFragment


    override fun onButtonClick(button: View) {
        when (button.id) {
            R.id.go_back_btn -> view.popBackStack()
            R.id.share_btn -> view.sharePhoto()
        }
    }

    override fun onViewCreated(context: Context) {
        val isFirstRun = context.getSharedPreferences("pref", 0).getBoolean("isFirstRun", true)
        if (isFirstRun) view.showHint()
        view.loadImage()
    }
}