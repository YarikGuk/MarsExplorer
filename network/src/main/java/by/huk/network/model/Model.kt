package by.huk.network.model

import by.huk.network.network.crypto.SpaceService
import by.huk.network.source.dto.space.MarsResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Model @Inject constructor(private val service: SpaceService) {

    fun getPhotoList():Observable<MarsResponse>{

        return service.getPhotoEntityList()
    }


}