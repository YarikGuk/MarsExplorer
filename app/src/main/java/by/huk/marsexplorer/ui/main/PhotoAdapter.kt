package by.huk.marsexplorer.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.huk.marsexplorer.R
import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide

class PhotoAdapter(val context: Context, val mainPresenter: IMainPresenter) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var photoList = ArrayList<PhotoEntity>()


    fun initialize(list: List<PhotoEntity>) {
        if (photoList.isNullOrEmpty()) {
            photoList = list.toMutableList() as ArrayList<PhotoEntity>
            notifyDataSetChanged()
        }
    }


    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemView: View, position: Int) {

            val item = photoList[position]

            val paint = binding.showImageButton.paint
            val width = paint.measureText(binding.showImageButton.text.toString())
            val textShader =
                LinearGradient(0f, 0f, width, binding.showImageButton.textSize, intArrayOf(
                    Color.parseColor("#C00F9E"),
                    Color.parseColor("#DB3259"),
                ), null, Shader.TileMode.REPEAT)

            with(binding) {
                showImageButton.paint.shader = textShader
                title.text = item.name
                subtitle.text = item.fullName
                Glide.with(context).load(item.imgSrc).into(this.photo)
            }

            binding.itemContainer.setOnClickListener {
                mainPresenter.onItemCLick(item.imgSrc)
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PhotoAdapter.PhotoViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemPhotoBinding>(LayoutInflater.from(parent.context),
                R.layout.item_photo,
                parent,
                false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        holder.bind(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }


}