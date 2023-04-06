package uz.behzoddev.izohcommon.string

import uz.behzoddev.izohcommon.level.LogLevel
import java.io.PrintWriter
import java.io.StringWriter

private const val MAX_BUFFER_SIZE = 256

public fun Throwable.stringify(): String {
  val sw = StringWriter(MAX_BUFFER_SIZE)
  val pw = PrintWriter(sw, false)
  printStackTrace(pw)
  pw.flush()
  return sw.toString()
}

public fun LogLevel.asString(): String {
  return when (this) {
    LogLevel.VERBOSE -> "V"
    LogLevel.DEBUG -> "D"
    LogLevel.INFO -> "I"
    LogLevel.WARNING -> "W"
    LogLevel.FAILURE -> "F"
  }
}
