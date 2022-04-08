package commondata

interface DataMapper<E, D> {

    fun fromData(entity: E): D

    fun toData(domain: D): E
}