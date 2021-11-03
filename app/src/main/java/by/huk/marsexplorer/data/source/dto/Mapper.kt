package by.huk.marsexplorer.data.source.dto

import javax.inject.Inject

interface Mapper<R,E>{
    fun map (from:R):E
}