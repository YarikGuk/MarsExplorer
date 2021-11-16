package by.huk.network.network.crypto

import by.huk.network.source.dto.space.MarsResponse
import by.huk.network.utils.API_KEY
import by.huk.network.utils.PAGE
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceService {


    @GET("photos")
    fun getPhotoEntityList(
        @Query("sol") sol: Int = 1000,
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int = PAGE
    ): Observable<MarsResponse>

}