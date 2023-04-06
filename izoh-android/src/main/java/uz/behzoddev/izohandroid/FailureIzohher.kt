package uz.behzoddev.izohandroid

import uz.behzoddev.izohandroid.core.Izohher
import uz.behzoddev.izohcommon.date.DateUtils
import uz.behzoddev.izohcommon.level.LogLevel
import uz.behzoddev.izohcommon.string.asString
import uz.behzoddev.izohcommon.string.stringify
import java.text.DateFormat

class FailureIzohher : Izohher {

  override fun log(logLevel: LogLevel, tag: String, message: String, throwable: Throwable?) {
    val currentMessage = composedAndPrint(logLevel, tag, message)
    val finalMessage = failureMessage(throwable, currentMessage)
    sanitizeAndLogPriority(logLevel, finalMessage)
  }

  private fun sanitizeAndLogPriority(logLevel: LogLevel, message: String) {
    when (logLevel) {
      LogLevel.FAILURE -> System.err.println(message)
      else -> println(message)
    }
  }

  private fun failureMessage(failure: Throwable?, currentMessage: String): String {
    return failure?.let {
      "$currentMessage\n${it.stringify()}"
    } ?: currentMessage
  }

  private fun composedAndPrint(logLevel: LogLevel, tag: String, message: String): String {
    val now = formatAndCurrentTime()
    val currentThread = runCurrentThread()
    return "$now ($currentThread) [${logLevel.asString()}/$tag]: $message"
  }

  private fun formatAndCurrentTime(): String {
    val dateFormat: DateFormat = DateUtils.simpleCurrentTime()
    return dateFormat.format(DateUtils.now())
  }

  private fun runCurrentThread(): String {
    return Thread.currentThread().run { "$name:$id" }
  }
}
