package by.huk.marsexplorer.ui.details

import android.view.View
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R

class DetailPresenterImpl(detailsFragment: DetailsFragment) : IDetailPresenter {
    val view: DetailContractView = detailsFragment

    override fun onButtonClick(button: View) {
        when (button.id) {
            R.id.go_back_btn -> view.popBackStack()
            R.id.share_btn -> view.sharePhoto()
        }

    }

    override fun onViewCreated() {
        if (!App.INSTANCE.hintIsShowed) view.showHint()
        view.loadImage()
    }


}