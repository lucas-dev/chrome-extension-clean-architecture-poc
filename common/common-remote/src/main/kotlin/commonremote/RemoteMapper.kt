package commonremote

interface RemoteMapper<in M, out E> {
    fun fromRemote(model: M): E
}