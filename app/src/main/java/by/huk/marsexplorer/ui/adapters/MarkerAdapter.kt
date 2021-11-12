package by.huk.marsexplorer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.huk.marsexplorer.R
import by.huk.marsexplorer.databinding.ItemCoordinateBinding
import by.huk.marsexplorer.ui.map.MapPresenterImpl
import com.google.android.gms.maps.model.Marker

class MarkerAdapter(val context: MapPresenterImpl) :
    RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>() {

    private var markerList = ArrayList<Marker>()

    fun addMarker(marker: Marker){
        if (markerList.isNullOrEmpty()) {
            markerList = arrayListOf(marker)
        }else{
        markerList.add(marker)
        }
        notifyDataSetChanged()
    }

    fun removeMarker(marker: Marker){
        val index = markerList.indexOf(marker)
        markerList.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class MarkerViewHolder(private val binding: ItemCoordinateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemView: View, position: Int) {

            val item = markerList[position]

            with(binding) {
                longitude.text = item.position.longitude.toString()
                latitude.text = item.position.latitude.toString()
                markerName.text = item.title

                deleteMarker.setOnClickListener {
                    item.remove()
                    removeMarker(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MarkerViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemCoordinateBinding>(LayoutInflater.from(parent.context),
                R.layout.item_coordinate,
                parent,
                false)
        return MarkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder.bind(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return markerList.size
    }


}