package uz.behzoddev.izohcore

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

public fun Priority.stringify(): String = when (this) {
  Priority.VERBOSE -> "V"
  Priority.DEBUG -> "D"
  Priority.INFO -> "I"
  Priority.WARNING -> "W"
  Priority.FAILURE -> "E"
}

public fun LoggingLevel.asString(): String {
  return when (this) {
    LoggingLevel.VERBOSE -> "V"
    LoggingLevel.DEBUG -> "D"
    LoggingLevel.INFO -> "I"
    LoggingLevel.WARNING -> "W"
    LoggingLevel.FAILURE -> "F"
  }
}
