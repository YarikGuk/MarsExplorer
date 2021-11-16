package by.huk.network.source.dto.space


import com.google.gson.annotations.SerializedName

data class MarsResponse(
    @SerializedName("photos")
    val photos: List<Photo?>? = null
) {
    data class Photo(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("camera")
        val camera: Camera? = null,
        @SerializedName("rover")
        val rover: Rover? = null,
        @SerializedName("img_src")
        val imgSrc: String? = null,
        @SerializedName("sol")
        val sol: Int? = null
    ) {
        data class Camera(
            @SerializedName("full_name")
            val fullName: String? = null,
        )
        data class Rover(
            @SerializedName("name")
            val name: String? = null
        )
    }
}