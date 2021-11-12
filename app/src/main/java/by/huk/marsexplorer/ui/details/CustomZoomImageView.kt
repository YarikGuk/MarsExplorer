package by.huk.marsexplorer.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import by.huk.marsexplorer.ui.details.CustomZoomImageView.State.*

class CustomZoomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttrs) {

    private enum class State{
        NONE, DRAG, ZOOM
    }
    private var mode = NONE

    private var savedMatrix = Matrix()
    private var matrx = Matrix()
    private var start = PointF()
    private var mid = PointF()
    private var oldDist = 1f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        if (event != null) {
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    savedMatrix.set(matrx)
                    start.set(event.x, event.y)
                    mode = DRAG
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    oldDist = distance(event)
                    if (oldDist > 10f) {
                        savedMatrix.set(matrx)
                        midPoint(mid, event)
                        mode = ZOOM
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    mode = NONE
                }
                MotionEvent.ACTION_MOVE -> when (mode) {
                    DRAG -> {
                        matrx.set(savedMatrix)
                        matrx.postTranslate(event.x - start.x, event.y - start.y)
                    }
                    ZOOM -> {
                        val newDist = distance(event)
                        if (newDist > 10f) {
                            matrx.set(savedMatrix)
                            val scale: Float = newDist / oldDist
                            matrx.postScale(scale, scale, mid.x, mid.y)
                        }
                    }
                    NONE -> mode = NONE
                }
            }
            this.imageMatrix = matrx
            return true
        } else return false
    }

    private fun distance(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return kotlin.math.sqrt(x * x + y * y)
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point[x / 2] = y / 2
    }


}
