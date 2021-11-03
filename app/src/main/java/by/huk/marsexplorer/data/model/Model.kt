package by.huk.marsexplorer.data.model

import by.huk.marsexplorer.data.network.crypto.SpaceService
import by.huk.marsexplorer.data.source.dto.space.MarsResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Model @Inject constructor(private val service:SpaceService) {

    fun getPhotoList():Observable<MarsResponse>{
        return service.getPhotoEntityList()
    }

}