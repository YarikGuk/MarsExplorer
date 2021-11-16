package by.huk.network.source.dto


interface Mapper<R,E>{
    fun map (from:R):E
}