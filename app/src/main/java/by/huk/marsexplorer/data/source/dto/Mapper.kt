package by.huk.marsexplorer.data.source.dto


interface Mapper<R,E>{
    fun map (from:R):E
}