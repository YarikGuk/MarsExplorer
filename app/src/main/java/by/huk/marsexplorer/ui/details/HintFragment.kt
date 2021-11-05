package by.huk.marsexplorer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import by.huk.marsexplorer.App
import by.huk.marsexplorer.R
import by.huk.marsexplorer.utils.Screens
import com.bumptech.glide.Glide

class HintFragment(private val photoUrl: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_hint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.image_back)

        Glide.with(requireContext()).load(photoUrl).into(image)
        App.INSTANCE.hintIsShowed = true

        view.findViewById<ConstraintLayout>(R.id.hint_container).setOnClickListener {
            App.INSTANCE.router.backTo(Screens.details(photoUrl))
        }
    }

}