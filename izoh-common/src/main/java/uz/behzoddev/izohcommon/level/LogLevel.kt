package uz.behzoddev.izohcommon.level

import android.util.Log

enum class LogLevel(
  val value: Int
) {
  VERBOSE(value = 2),
  DEBUG(value = 3),
  INFO(value = 4),
  WARNING(value = 5),
  FAILURE(value = 6)
}

fun LogLevel.asAndroidLevel(): Int {
  return when (this) {
    LogLevel.VERBOSE -> Log.VERBOSE
    LogLevel.DEBUG -> Log.DEBUG
    LogLevel.INFO -> Log.INFO
    LogLevel.WARNING -> Log.WARN
    LogLevel.FAILURE -> Log.ERROR
    else -> Log.ERROR
  }
}
