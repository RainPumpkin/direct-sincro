package ps.g08.directsincro.service.mappers

interface IMapper<O, R> {
    fun single(obj: O): R
    fun multiple(objs: List<O>): List<R>
}