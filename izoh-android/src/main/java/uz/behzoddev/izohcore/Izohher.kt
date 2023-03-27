package uz.behzoddev.izohcore

interface Izohher {
    fun log(level: LoggingLevel, tag: String, message: String, throwable: Throwable? = null)
}