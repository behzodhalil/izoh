package uz.behzoddev.izohcore

import android.util.Log

enum class Level(
  val value: Int
) {
  VERBOSE(value = 2),
  DEBUG(value = 3),
  INFO(value = 4),
  WARNING(value = 5),
  FAILURE(value = 6)
}

fun Level.asAndroidLevel(): Int {
  return when (this) {
    Level.VERBOSE -> Log.VERBOSE
    Level.DEBUG -> Log.DEBUG
    Level.INFO -> Log.INFO
    Level.WARNING -> Log.WARN
    Level.FAILURE -> Log.ERROR
  }
}
