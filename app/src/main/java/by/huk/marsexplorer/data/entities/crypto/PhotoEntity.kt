package by.huk.marsexplorer.data.entities.crypto

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    val id: Int,
    val imgSrc: String,
    val sol: Int,
    val fullName: String,
    val name: String
    )
