package by.huk.marsexplorer.utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomDialog(context: Context):MaterialAlertDialogBuilder(context) {
    override fun setView(view: View?): MaterialAlertDialogBuilder {
        return super.setView(view)

    }


    //    val paint = binding.showImageButton.paint
//    val width = paint.measureText(binding.showImageButton.text.toString())
//    val textShader =
//        LinearGradient(0f, 0f, width, binding.showImageButton.textSize, intArrayOf(
//            Color.parseColor("#C00F9E"),
//            Color.parseColor("#DB3259"),
//        ), null, Shader.TileMode.REPEAT)


}