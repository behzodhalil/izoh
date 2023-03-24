package uz.behzoddev.izohcore

import uz.behzoddev.izohcore.extension.DateUtils
import java.text.DateFormat

object FailureIzohLogger : IzohLogger {

    override fun log(priority: Priority, tag: String, message: String, failure: Throwable?) {
        val currentMessage = composedAndPrint(priority, tag, message)
        val finalMessage = failureMessage(failure, currentMessage)
        sanitizeAndLogPriority(priority, finalMessage)
    }

    private fun sanitizeAndLogPriority(priority: Priority, message: String) {
        when (priority) {
            Priority.FAILURE -> System.err.println(message)
            else -> println(message)
        }
    }

    private fun failureMessage(failure: Throwable?, currentMessage: String): String {
        return failure?.let {
            "$currentMessage\n${it.stringify()}"
        } ?: currentMessage
    }

    private fun composedAndPrint(priority: Priority, tag: String, message: String): String {
        val now = formatAndCurrentTime()
        val currentThread = runCurrentThread()
        return "$now ($currentThread) [${priority.stringify()}/$tag]: $message"
    }

    private fun formatAndCurrentTime(): String {
        val dateFormat: DateFormat = DateUtils.simpleCurrentTime()
        return dateFormat.format(DateUtils.now())
    }

    private fun runCurrentThread(): String {
        return Thread.currentThread().run { "$name:$id" }
    }
}