package by.huk.marsexplorer.data.source.dto.mappers

import by.huk.marsexplorer.data.entities.crypto.PhotoEntity
import by.huk.marsexplorer.data.source.dto.Mapper
import by.huk.marsexplorer.data.source.dto.space.MarsResponse
import javax.inject.Inject

class MarsResponseMapper @Inject constructor():Mapper<MarsResponse.Photo?,PhotoEntity> {
    override fun map(from: MarsResponse.Photo?): PhotoEntity {
        return PhotoEntity(
            id = from?.id?:0,
            imgSrc = from?.imgSrc.orEmpty(),
            sol = from?.sol?:0,
            fullName = from?.camera?.fullName.orEmpty(),
            name = from?.rover?.name.orEmpty()

        )
    }
}