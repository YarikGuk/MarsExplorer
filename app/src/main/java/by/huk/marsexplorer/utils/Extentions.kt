package by.huk.marsexplorer.utils

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat


fun ImageView.applyLoopingAnimatedVectorDrawable(@DrawableRes avdResId: Int, enable:Boolean) {
    val animated = AnimatedVectorDrawableCompat.create(context, avdResId)
    animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable?) {
            this@applyLoopingAnimatedVectorDrawable.post { animated.start() }
        }
    })
    this.setImageDrawable(animated)
    if (enable) animated?.start()
    else animated?.stop()
}


