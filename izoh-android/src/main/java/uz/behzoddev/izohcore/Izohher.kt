package uz.behzoddev.izohcore

interface Izohher {
  fun log(level: Level, tag: String, message: String, throwable: Throwable? = null)
}
