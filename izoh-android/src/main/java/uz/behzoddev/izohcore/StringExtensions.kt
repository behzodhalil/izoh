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

public fun Level.asString(): String {
  return when (this) {
    Level.VERBOSE -> "V"
    Level.DEBUG -> "D"
    Level.INFO -> "I"
    Level.WARNING -> "W"
    Level.FAILURE -> "F"
  }
}
